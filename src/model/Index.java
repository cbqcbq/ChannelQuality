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

	// 光缆使用未超10年比率
	private double fib10;
	// 设备使用未超10年比率
	private double equ10;
	// 光缆交接箱使用未超10年比率
	private double box10;
	// 危急缺陷次数
	private double criDef;
	// 严重缺陷次数
	private double serDef;
	// 一般缺陷次数
	private double genDef;
	// 重大故障次数
	private double majFau;
	// 一般故障次数
	private double genFau;
	// 严重故障次数
	private double serFau;
	// 平均故障恢复时间
	private double aveFauRecTim;
	// 设备维护等级
	private double equMaiLev;
	// 设备冗余系数
	private double equRedFac;
	// 应急保障等级
	private double emeProLev;
	// 时延抖动次数
	private double delJit;
	// 误码率
	private double bitErrRat;
	// 丢包率
	private double pacLosRat;
	// 带宽利用率
	private double banUti;
	// 信噪比
	private double sigNoiRat;
	// 阻塞率
	private double bloRat;
	// 吞吐量
	private double thr;
	// 流量峰值速率
	private double peaFloRat;
	// 流量平均速率
	private double traRat;
	// 最大突发长度
	private double maxburLen;
	// 配电自动化业务未中断率
	private double powDisAutBusWitIntRat;
	// 智能用电业务未中断率
	private double intPowSerWitIntRat;
	// 用电信息采集系统业务未中断率
	private double powConSysWitIntRat;
	// 平均中断时间
	private double aveIntTim;
	// 配电自动化业务无错率
	private double powDisAutBusWitErrRat;
	// 智能用电业务无错率
	private double intPowSerWitErrRat;
	// 用电信息采集系统业务无错率
	private double powConSysWitErrRat;
	// 业务响应未超时率
	private double busResNotTimRat;
	// 平均用电信息采集系统业务服务时间
	private double avePowConSysSerTim;
	// 平均配电自动化业务服务时间
	private double avePowDisAutBusSerTim;
	// 平均智能用电业务服务时间
	private double aveIntPowSerSerTim;
	// 安全保护等级
	private double secLevel;
	// 错操作次数
	private double wroOpe;
	// 恶意篡改次数
	private double malTam;
	// 期望输出值
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
