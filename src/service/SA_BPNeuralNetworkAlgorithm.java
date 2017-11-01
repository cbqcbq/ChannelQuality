package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SA_BPNeuralNetworkAlgorithm {

	public static void main(String[] args) {
	}

	// Boltzmann����Ϊ1.3806505(24) �� 10-23 J/K��̫С�˴ﲻ��Ҫ��
	private static final double K = 0.0000000000000001;
	// ������������ֵ�Ĵ����ﵽ��ֵ�����ﵽƽ��״̬
	private static final int isBalance = 5;
	// �����ڵ���
	private static final int M = 37;
	// ������ڵ���
	private static final int N = 75;
	// �����ڵ���
	private static final int P = 1;
	// ����㵽������֮�������Ȩֵ
	private double[][] weight1;
	// �����㵽�����֮�������Ȩֵ
	private double[][] weight2;
	// ����㵽������֮�������Ȩֵ��ʱ�洢
	private double[][] weightT1;
	// �����㵽�����֮�������Ȩֵ��ʱ�洢
	private double[][] weightT2;
	// ��������������
	private double[] hideLayerInput = new double[N];
	private double[] hideLayerOutput = new double[N];
	// �������������
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
	 * ���һ����-1,1�����Ȩ��
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
	 * ������
	 * 
	 * @param sample
	 *            ѵ������Դ
	 * @param k
	 *            ����ָ��
	 * @param expectOutput
	 *            ����Դ�������
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
		// ����ƽ���ĺ�
		double di = 0.0;
		// �������ڵ����
		double[] diff = new double[P];
		for (i = 0; i < P; i++) {
			diff[i] = outputLayerOutput[i] - expectOutput[k];// ��������ڵ㲻ֻһ���Ļ��������Ǵ��,��Ϊ�������ֵֻ��һ��
			// System.out.println("diff[" + times + "]=" + diff[i]);
			di += Math.pow(diff[i], 2);
			// System.out.println("di=" + di);
		}
		return 0.5 * di;
	}

	/**
	 * 
	 * @param sample
	 *            ѵ������Դ
	 * @param expectOutput
	 *            ����Դ�������
	 * @param threshold
	 *            �����ֵ
	 * @param initTemp
	 *            ��ʼ�¶�
	 * @param minTemp
	 *            ����¶�
	 * @param maxdisturbCount
	 *            ÿ���¶��Ŷ���������
	 */
	public void startTraining(double[][] sample, double[] expectOutput,
			double threshold, double initTemp, double minTemp,
			int maxdisturbCount) {
		// ����
		double deviation = Integer.MAX_VALUE;
		// ����temp
		double deviationTemp = 0;
		// ����temp������֮��
		double di = 0.0;
		// ѵ������
		int times = 0;
		// ��������
		int sampleCount = sample.length;
		// �ж��Ƿ�ﵽƽ��״̬���ݣ���������������ֵ�ﵽisBalance����
		int Bcount = 0;

		int i, j, k, p;
		double t;

		all: for (k = 0; k < sampleCount; k++) {
			// ��������
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
		//�˴�Ӧ���б�������Ȩֵ�Ĳ���
	}

	/**
	 * �Ƿ������ֵ
	 * 
	 * @param di
	 *            �¾�����֮��
	 * @param t
	 *            ��ǰ�¶�
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
	 * ����µ�����Ȩֵ
	 */
	private void getNewWeight() {
		int i, j;
		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				double r = Math.random();
				weight1[i][j] += r * sgn(r) * 2;// 2����Ϊ����Ȩֵ�������ǡ�-1,1��
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
