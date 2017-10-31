package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SA_BPNeuralNetworkAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Boltzmann常数为1.3806505(24) × 10-23 J/K，太小了忽略不计
	private static final double K = 0.0;
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
		DecimalFormat df = new DecimalFormat("#.00");
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				weight1[i][j] = Double.parseDouble(df.format(Math.random()));
				weightT1 = weight1.clone();
			}
		}
		for (i = 0; i < N; i++) {
			for (j = 0; j < P; j++) {
				weight2[i][j] = Double.parseDouble(df.format(Math.random()));
				weightT2 = weight2.clone();
			}
		}
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
		List<PicData> list = new ArrayList<PicData>();
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
		// 扰动次数
		int Mcount = 0;
		// 判断是否达到平衡状态依据，当连续不接受新值达到isBalance次数
		int Bcount = 0;

		int i, j, k, p;
		double t;

		all:for (k = 0; k < sampleCount; k++) {
			// 迭代次数
			int n = 1;
			two:for (t = initTemp; t > minTemp; t = initTemp / (1 + n++)) {
				deviation = getDeviation(sample, k, expectOutput);
				if (deviation < threshold) {
					break all;
				}
				for (p = 0; p < maxdisturbCount; p++) {
					getNewWeight();
					deviationTemp = getDeviation(sample, k, expectOutput);
					di = deviationTemp - deviation;
					if (di < 0||acceptNew(di,t)) {
						Bcount=0;
						deviation=deviationTemp;
						if (deviation < threshold) {
							break all;
						}
					}else{
						Bcount++;
					}
					if(Bcount>=isBalance){
						break two;
					}
				}
			}
		}
		while (deviation > threshold && times < trainingTimes
				&& index < sampleCount) {

			double[] temp = new double[P];
			for (i = 0; i < P; i++) {
				temp[i] = diff[i] * outputLayerOutput[i]
						* (1 - outputLayerOutput[i]);
			}
			for (i = 0; i < N; i++) {
				for (j = 0; j < P; j++) {
					wDiffTemp2[i][j] = (1 - f) * r * temp[j]
							* hideLayerOutput[i] + f * wDiff2[i][j];
					// wDiffTemp2[i][j] = r * temp[j] * hideLayerOutput[i] + f
					// * wDiff2[i][j];
					// System.out.println("improve:times:"+times+" wDiffTemp2["+i+"]["+j+"]="+wDiffTemp2[i][j]);
				}
			}
			for (i = 0; i < M; i++) {
				for (j = 0; j < N; j++) {
					for (k = 0; k < P; k++) {
						wDiffTemp1[i][j] = (1 - f) * r * sample[index][i]
								* temp[k] * hideLayerOutput[j]
								* (1 - hideLayerOutput[j]) * wDiffTemp2[j][k]
								+ f * wDiff1[i][j];
						// wDiffTemp1[i][j] = r * sample[index][i] * temp[k]
						// * hideLayerOutput[j] * (1 - hideLayerOutput[j])
						// * wDiffTemp2[j][k] + f * wDiff1[i][j];
						// System.out.println("improve:times:"+times+" wDiffTemp1["+i+"]["+j+"]="+wDiffTemp1[i][j]);
					}
				}
			}
			wDiff2 = wDiffTemp2.clone();
			wDiff1 = wDiffTemp1.clone();
			if (deviation > 1.04 * deviationTemp) {
				f = 0;
				r = 0.7 * r;
			} else if (deviation < deviationTemp) {
				f = 0.4;
				r = 1.05 * r;
			}
			deviationTemp = deviation;
			for (i = 0; i < M; i++) {
				for (j = 0; j < N; j++) {
					weight1[i][j] -= wDiff1[i][j];
				}
			}
			for (i = 0; i < N; i++) {
				for (j = 0; j < P; j++) {
					weight2[i][j] -= wDiff2[i][j];
				}
			}
			times++;
			index++;
			PicData picData = new PicData();
			picData.setDeviation(deviation);
			picData.setTimes(times);
			list.add(picData);
		}
		ExportExcel excel = new ExportExcel();
		excel.exportExcel(list, "C:/Users/admin/Desktop/picdata.xls");
	}

	private boolean acceptNew(double di, double t) {
		// TODO Auto-generated method stub
		return false;
	}

	private void getNewWeight() {
		// TODO Auto-generated method stub

	}

}
