package kmeans.main.algo;

import java.util.*;
import java.util.stream.Collectors;

public class KMeans {
	
	private int intArr[];
	private int clusters[];
	private List<List<Integer>> prevClusters;
	private int maxIter;
	private boolean trained;
	private int iteration = 0;
	private List<List<Integer>> clusterAssignments;
	
	public KMeans(int[] data, int clusters, int iters) {
		this.trained = false;
		this.clusterAssignments = new ArrayList<>();
		this.intArr =   Arrays.copyOf(data, data.length);
		this.clusters = new int[clusters];
		Random random = new Random();

		for (int i = 0; i < clusters; i++) {
			this.clusters[i] = random.nextInt(255);
			this.clusterAssignments.add(new ArrayList<>());
		}

		this.prevClusters = new ArrayList<>();
		this.maxIter = iters;
	}

	private int EuclideanDistance(int x, int y) {
		return Math.abs(x - y);
	}

	private int closestClusterIndex(int point) {
		int minDistance = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = 0; i < clusters.length; i++) {
			int dist = EuclideanDistance(clusters[i], point);
			if (dist < minDistance) {
				minDistance = dist;
				minIndex = i;
			}

			if (minDistance == 0) {
				break;
			}
		}

		return minIndex;
	}

	public boolean fit() {
		try {
			while (true) {
				if ((iteration > maxIter)) {
					System.out.println("Max Iters");
					break;
				}

				if (iteration != 0 && Arrays.stream(this.clusters).boxed().equals(prevClusters.get(prevClusters.size() - 1))) {
					System.out.println("Converged");
					break;
				}

				this.prevClusters.add(Arrays.stream(this.clusters).boxed().collect(Collectors.toList()));

				this.clusterAssignments.parallelStream().forEach(cluster -> cluster.clear());

				for (int i = 0; i < intArr.length; i++) {
					this.clusterAssignments.get(closestClusterIndex(intArr[i])).add(intArr[i]);
				}

				for (int i = 0;i < clusters.length; i++) {
					this.clusters[i] = (int) this.clusterAssignments.get(i).stream().mapToInt(k -> k).average().orElse(0);
				}
				System.out.println((int) (iteration*100.0/this.maxIter) + "%");


				iteration++;
				
			}
		
			this.trained = true;
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public int[] predict()  {
		if (!this.trained) {
			throw new RuntimeException("Model Is Not Trained");
		}

		int[] returnData = new int[intArr.length];
		
		for (int i = 0; i < intArr.length; i++) {
			returnData[i] = this.clusters[this.closestClusterIndex(intArr[i])];
		}
		return returnData;
	}
}