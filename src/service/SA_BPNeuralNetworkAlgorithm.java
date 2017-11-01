package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SA_BPNeuralNetworkAlgorithm {

	public static void main(String[] args) {
	}

	// Boltzmann常数为1.3806505(24) × 10-23 J/K，太小了达不到要求
	private static final double K = 0.0000000000000001;
	// 连续不接受新值的次数达到此值，即达到平衡状态
	private static final int isBalance = 5;
	// 输入层节点数
	private static final int M = 37;
	// 隐含层节点数
	private static final int N = 75;
	// 输出层节点数
	private static final int P = 1;
	// 输入层到隐含层之间的连接权值
	private double[][] weight1;
	// 隐含层到输出层之间的连接权值
	private double[][] weight2;
	// 输入层到隐含层之间的连接权值临时存储
	private double[][] weightT1;
	// 隐含层到输出层之间的连接权值临时存储
	private double[][] weightT2;
	// 隐含层输入和输出
	private double[] hideLayerInput = new double[N];
	private double[] hideLayerOutput = new double[N];
	// 输出层输入和输出
	private double[] outputLayerInput = new double[P];
	private double[] outputLayerOutput = new double[P];

	public SA_BPNeuralNetworkAlgorithm() {
		weight1 = new double[M][N];
		weight2 = new double[N][P];
		int i, j;
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				weight1[i][j] = getRandWeight();
				weightT1[i][j] = weight1[i][j];
			}
		}
		for (i = 0; i < N; i++) {
			for (j = 0; j < P; j++) {
				weight2[i][j] = getRandWeight();
				weightT2[i][j] = weight2[i][j];
			}
		}
	}

	/**
	 * 获得一个【-1,1】随机权重
	 * 
	 * @return
	 */
	private double getRandWeight() {
		DecimalFormat df = new DecimalFormat("#.00");
		Random random = new Random();
		double res = Double.parseDouble(df.format(Math.random()
				* (random.nextInt(2) == 0 ? -1 : 1)));
		System.out.println("The random weight is " + res);
		return res;
	}

	/**
	 * 获得误差
	 * 
	 * @param sample
	 *            训练数据源
	 * @param k
	 *            样本指针
	 * @param expectOutput
	 *            数据源期望输出
	 * @return
	 */
	private double getDeviation(double[][] sample, int k, double[] expectOutput) {
		int i, j;
		Arrays.fill(hideLayerInput, 0);
		for (i = 0; i < N; i++) {
			for (j = 0; j < M; j++) {
				hideLayerInput[i] += weight1[j][i] * sample[k][j];
			}
			hideLayerOutput[i] = 1 / (1 + Math.exp(-hideLayerInput[i]));
			// System.out.println("hideLayerOutput[" + i + "]="
			// + hideLayerOutput[i]);
		}
		Arrays.fill(outputLayerInput, 0);
		for (i = 0; i < P; i++) {
			for (j = 0; j < N; j++) {
				outputLayerInput[i] += weight2[j][i] * hideLayerOutput[j];
			}
			outputLayerOutput[i] = 1 / (1 + Math.exp(-outputLayerInput[i]));
			// System.out.println("outputLayerOutput[" + i + "]="
			// + outputLayerOutput[i]);
		}
		// 误差的平方的和
		double di = 0.0;
		// 输出层各节点误差
		double[] diff = new double[P];
		for (i = 0; i < P; i++) {
			diff[i] = outputLayerOutput[i] - expectOutput[k];// 如果输出层节点不只一个的话这句代码是错的,因为期望输出值只有一个
			// System.out.println("diff[" + times + "]=" + diff[i]);
			di += Math.pow(diff[i], 2);
			// System.out.println("di=" + di);
		}
		return 0.5 * di;
	}

	/**
	 * 
	 * @param sample
	 *            训练数据源
	 * @param expectOutput
	 *            数据源期望输出
	 * @param threshold
	 *            误差阈值
	 * @param initTemp
	 *            初始温度
	 * @param minTemp
	 *            最低温度
	 * @param maxdisturbCount
	 *            每个温度扰动次数上限
	 */
	public void startTraining(double[][] sample, double[] expectOutput,
			double threshold, double initTemp, double minTemp,
			int maxdisturbCount) {
		// 误差函数
		double deviation = Integer.MAX_VALUE;
		// 误差函数temp
		double deviationTemp = 0;
		// 误差函数temp与误差函数之差
		double di = 0.0;
		// 训练次数
		int times = 0;
		// 样本总数
		int sampleCount = sample.length;
		// 判断是否达到平衡状态依据，当连续不接受新值达到isBalance次数
		int Bcount = 0;

		int i, j, k, p;
		double t;

		all: for (k = 0; k < sampleCount; k++) {
			// 迭代次数
			int n = 1;
			for (t = initTemp; t > minTemp; t = initTemp / (1 + n++)) {
				deviation = getDeviation(sample, k, expectOutput);
				if (deviation < threshold) {
					break all;
				}
				Bcount = 0;
				for (p = 0; p < maxdisturbCount; p++) {
					getNewWeight();
					deviationTemp = getDeviation(sample, k, expectOutput);
					di = deviationTemp - deviation;
					if (di < 0 || acceptNew(di, t)) {
						Bcount = 0;
						deviation = deviationTemp;
						weightT1 = weight1.clone();
						weightT2 = weight2.clone();
						if (deviation < threshold) {
							break all;
						}
					} else {
						Bcount++;
						weight1 = weightT1.clone();
						weight2 = weightT2.clone();
					}
					if (Bcount >= isBalance) {
						break;
					}
				}
			}
		}
		//此处应该有保存连接权值的操作
	}

	/**
	 * 是否接受新值
	 * 
	 * @param di
	 *            新旧误差函数之差
	 * @param t
	 *            当前温度
	 * @return
	 */
	private boolean acceptNew(double di, double t) {
		double r = Math.random();
		double p = Math.exp(-di / (K * t));
		if (p > r)
			return true;
		else
			return false;
	}

	/**
	 * 获得新的连接权值
	 */
	private void getNewWeight() {
		int i, j;
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				double r = Math.random();
				weight1[i][j] += r * sgn(r) * 2;// 2是因为连接权值的区间是【-1,1】
			}
		}
		for (i = 0; i < N; i++) {
			for (j = 0; j < P; j++) {
				double r = Math.random();
				weight2[i][j] += r * sgn(r) * 2;
			}
		}
	}

	private int sgn(double r) {
		if (r > 0.5)
			return 1;
		else if (r < 0.5)
			return -1;
		else
			return 0;
	}

}
