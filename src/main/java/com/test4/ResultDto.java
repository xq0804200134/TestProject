package com.test4;

public class ResultDto {
	private Integer id;

	// ��Ʒ��Ӧ���㷨
	private String alg;

	// ��Ʒ��Ӧ��detailҳ�����ӣ�����"http://www.alibaba.com/product-gs/244957087/digital_mp3_player_with_FM_radio.html
	private String productUrl;

	// ��Ʒ��Ӧ��Сͼ����listҳ����ʾ�Ĳ�ƷͼƬ�����硰http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.summ.jpg��
	private String summImagePath;

	// ��Ʒ��Ӧ����ͼ����listҳ�����ͼ�����硰http://i00.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg_200x200.jpg��
	private String scaleImagePath;
	
	 /**
     * ��ȡ��ƷĬ�ϵ�ͼƬ����listҳ����ʾ�Ĳ�ƷͼƬ�����硰http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg��
     */
    private String              defaultImagePath;

	// ��Ʒ����
	private String subject;

	// ��������,���硰UK,US,RMB������listҳ���С� FOB Price: US $9-13 / Piece���еĻ��ҵ�λ����
	private String currencyType;

	// ���ҷ���,���硰$��
	private String currencySymbol;

	// ��Ʒ�׼ۣ���listҳ���С� FOB Price: US $9-13 / Piece���еļ۸񲿷�
	private String lowerPrice;

	// ��Ʒ�߼�
	private String higherPrice;

	// ��Ʒ�۸�λ
	private String priceUnit;
	
	//������������
	private Integer saleProduct3Total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getSummImagePath() {
		return summImagePath;
	}

	public void setSummImagePath(String summImagePath) {
		this.summImagePath = summImagePath;
	}

	public String getScaleImagePath() {
		return scaleImagePath;
	}

	public void setScaleImagePath(String scaleImagePath) {
		this.scaleImagePath = scaleImagePath;
	}

	public String getDefaultImagePath() {
		return defaultImagePath;
	}

	public void setDefaultImagePath(String defaultImagePath) {
		this.defaultImagePath = defaultImagePath;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public String getHigherPrice() {
		return higherPrice;
	}

	public void setHigherPrice(String higherPrice) {
		this.higherPrice = higherPrice;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public Integer getSaleProduct3Total() {
		return saleProduct3Total;
	}

	public void setSaleProduct3Total(Integer saleProduct3Total) {
		this.saleProduct3Total = saleProduct3Total;
	}
}
