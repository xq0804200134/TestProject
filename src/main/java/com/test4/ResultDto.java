package com.test4;

public class ResultDto {
	// ��Ʒid
    private Long              id;

    // ��˾id
    private Long              companyId;

    // ��Ʒ��Ӧ���㷨
    private String            alg;

    // ��Ʒ��Ӧ��detailҳ�����ӣ�����"http://www.alibaba.com/product-gs/244957087/digital_mp3_player_with_FM_radio.html
    private String            productUrl;

    // ��Ʒ��Ӧ��Сͼ����listҳ����ʾ�Ĳ�ƷͼƬ�����硰http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.summ.jpg��
    private String            summImagePath;

    // ��Ʒ��Ӧ����ͼ����listҳ�����ͼ�����硰http://i00.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg_200x200.jpg��
    private String            scaleImagePath;

    private String            image140Path;

    /**
     * ��ȡ��ƷĬ�ϵ�ͼƬ����listҳ����ʾ�Ĳ�ƷͼƬ�����硰http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg��
     */
    private String            defaultImagePath;

    // ��Ʒ����
    private String            subject;

    // ��������,���硰UK,US,RMB������listҳ���С� FOB Price: US $9-13 / Piece���еĻ��ҵ�λ����
    private String            currencyType;

    // ���ҷ���,���硰$��
    private String            currencySymbol;

    // ��Ʒ�׼ۣ���listҳ���С� FOB Price: US $9-13 / Piece���еļ۸񲿷�
    private String            lowerPrice;

    // ��Ʒ�߼�
    private String            higherPrice;

    // ��Ʒ�۸�λ
    private String            priceUnit;

    // SEO title
    private String            seotitle;

    // ����������
    private String           saleInSixMonth;

    // ��Ʒ�ļ�Ҫ����
    private String            summary;

    // ��Ʒ��λ ����
    private String            oddUnitName;

    // ��Ʒ��λ����
    private String            multUnitName;
    
    // subjectǰ35���ַ�
    private String            simpleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public String getImage140Path() {
		return image140Path;
	}

	public void setImage140Path(String image140Path) {
		this.image140Path = image140Path;
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

	public String getSeotitle() {
		return seotitle;
	}

	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}

	public String getSaleInSixMonth() {
		return saleInSixMonth;
	}

	public void setSaleInSixMonth(String saleInSixMonth) {
		this.saleInSixMonth = saleInSixMonth;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOddUnitName() {
		return oddUnitName;
	}

	public void setOddUnitName(String oddUnitName) {
		this.oddUnitName = oddUnitName;
	}

	public String getMultUnitName() {
		return multUnitName;
	}

	public void setMultUnitName(String multUnitName) {
		this.multUnitName = multUnitName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
}
