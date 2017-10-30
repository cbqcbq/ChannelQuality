package model;

public class Index {
	private double[] target;

	public Index() {
		target = new double[38];
	}

	public double[] getTarget() {
		return target;
	}

	public void setTarget(double[] target) {
		this.target = target;
		fib10 = target[0];
		equ10 = target[1];
		box10 = target[2];
		criDef = target[3];
		serDef = target[4];
		genDef = target[5];
		majFau = target[6];
		genFau = target[7];
		serFau = target[8];
		aveFauRecTim = target[9];
		equMaiLev = target[10];
		equRedFac = target[11];
		emeProLev = target[12];
		delJit = target[13];
		bitErrRat = target[14];
		pacLosRat = target[15];
		banUti = target[16];
		sigNoiRat = target[17];
		bloRat = target[18];
		thr = target[19];
		peaFloRat = target[20];
		traRat = target[21];
		maxburLen = target[22];
		powDisAutBusWitIntRat = target[23];
		intPowSerWitIntRat = target[24];
		powConSysWitIntRat = target[25];
		aveIntTim = target[26];
		powDisAutBusWitErrRat = target[27];
		intPowSerWitErrRat = target[28];
		powConSysWitErrRat = target[29];
		busResNotTimRat = target[30];
		avePowConSysSerTim = target[31];
		avePowDisAutBusSerTim = target[32];
		aveIntPowSerSerTim = target[33];
		secLevel = target[34];
		wroOpe = target[35];
		malTam = target[36];
		expOutVal = target[37];
	}

	// ����ʹ��δ��10�����
	private double fib10;
	// �豸ʹ��δ��10�����
	private double equ10;
	// ���½�����ʹ��δ��10�����
	private double box10;
	// Σ��ȱ�ݴ���
	private double criDef;
	// ����ȱ�ݴ���
	private double serDef;
	// һ��ȱ�ݴ���
	private double genDef;
	// �ش���ϴ���
	private double majFau;
	// һ����ϴ���
	private double genFau;
	// ���ع��ϴ���
	private double serFau;
	// ƽ�����ϻָ�ʱ��
	private double aveFauRecTim;
	// �豸ά���ȼ�
	private double equMaiLev;
	// �豸����ϵ��
	private double equRedFac;
	// Ӧ�����ϵȼ�
	private double emeProLev;
	// ʱ�Ӷ�������
	private double delJit;
	// ������
	private double bitErrRat;
	// ������
	private double pacLosRat;
	// ����������
	private double banUti;
	// �����
	private double sigNoiRat;
	// ������
	private double bloRat;
	// ������
	private double thr;
	// ������ֵ����
	private double peaFloRat;
	// ����ƽ������
	private double traRat;
	// ���ͻ������
	private double maxburLen;
	// ����Զ���ҵ��δ�ж���
	private double powDisAutBusWitIntRat;
	// �����õ�ҵ��δ�ж���
	private double intPowSerWitIntRat;
	// �õ���Ϣ�ɼ�ϵͳҵ��δ�ж���
	private double powConSysWitIntRat;
	// ƽ���ж�ʱ��
	private double aveIntTim;
	// ����Զ���ҵ���޴���
	private double powDisAutBusWitErrRat;
	// �����õ�ҵ���޴���
	private double intPowSerWitErrRat;
	// �õ���Ϣ�ɼ�ϵͳҵ���޴���
	private double powConSysWitErrRat;
	// ҵ����Ӧδ��ʱ��
	private double busResNotTimRat;
	// ƽ���õ���Ϣ�ɼ�ϵͳҵ�����ʱ��
	private double avePowConSysSerTim;
	// ƽ������Զ���ҵ�����ʱ��
	private double avePowDisAutBusSerTim;
	// ƽ�������õ�ҵ�����ʱ��
	private double aveIntPowSerSerTim;
	// ��ȫ�����ȼ�
	private double secLevel;
	// ���������
	private double wroOpe;
	// ����۸Ĵ���
	private double malTam;
	// �������ֵ
	private double expOutVal;

	public double getFib10() {
		return fib10;
	}

	public double getEqu10() {
		return equ10;
	}

	public double getBox10() {
		return box10;
	}

	public double getCriDef() {
		return criDef;
	}

	public double getSerDef() {
		return serDef;
	}

	public double getGenDef() {
		return genDef;
	}

	public double getMajFau() {
		return majFau;
	}

	public double getGenFau() {
		return genFau;
	}

	public double getSerFau() {
		return serFau;
	}

	public double getAveFauRecTim() {
		return aveFauRecTim;
	}

	public double getEquMaiLev() {
		return equMaiLev;
	}

	public double getEquRedFac() {
		return equRedFac;
	}

	public double getEmeProLev() {
		return emeProLev;
	}

	public double getDelJit() {
		return delJit;
	}

	public double getBitErrRat() {
		return bitErrRat;
	}

	public double getPacLosRat() {
		return pacLosRat;
	}

	public double getBanUti() {
		return banUti;
	}

	public double getSigNoiRat() {
		return sigNoiRat;
	}

	public double getBloRat() {
		return bloRat;
	}

	public double getThr() {
		return thr;
	}

	public double getPeaFloRat() {
		return peaFloRat;
	}

	public double getTraRat() {
		return traRat;
	}

	public double getMaxburLen() {
		return maxburLen;
	}

	public double getPowDisAutBusWitIntRat() {
		return powDisAutBusWitIntRat;
	}

	public double getIntPowSerWitIntRat() {
		return intPowSerWitIntRat;
	}

	public double getPowConSysWitIntRat() {
		return powConSysWitIntRat;
	}

	public double getAveIntTim() {
		return aveIntTim;
	}

	public double getPowDisAutBusWitErrRat() {
		return powDisAutBusWitErrRat;
	}

	public double getIntPowSerWitErrRat() {
		return intPowSerWitErrRat;
	}

	public double getPowConSysWitErrRat() {
		return powConSysWitErrRat;
	}

	public double getBusResNotTimRat() {
		return busResNotTimRat;
	}

	public double getAvePowConSysSerTim() {
		return avePowConSysSerTim;
	}

	public double getAvePowDisAutBusSerTim() {
		return avePowDisAutBusSerTim;
	}

	public double getAveIntPowSerSerTim() {
		return aveIntPowSerSerTim;
	}

	public double getSecLevel() {
		return secLevel;
	}

	public double getWroOpe() {
		return wroOpe;
	}

	public double getMalTam() {
		return malTam;
	}

	public double getExpOutVal() {
		return expOutVal;
	}
}
