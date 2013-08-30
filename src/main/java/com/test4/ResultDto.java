package com.test4;

public class ResultDto {
	// 产品id
    private Long              id;

    // 公司id
    private Long              companyId;

    // 产品对应的算法
    private String            alg;

    // 产品对应的detail页面链接，例如"http://www.alibaba.com/product-gs/244957087/digital_mp3_player_with_FM_radio.html
    private String            productUrl;

    // 产品对应的小图，即list页面显示的产品图片，例如“http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.summ.jpg”
    private String            summImagePath;

    // 产品对应的中图，即list页面的中图，例如“http://i00.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg_200x200.jpg”
    private String            scaleImagePath;

    private String            image140Path;

    /**
     * 获取产品默认的图片，即list页面显示的产品图片，例如“http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg”
     */
    private String            defaultImagePath;

    // 产品名称
    private String            subject;

    // 货币类型,例如“UK,US,RMB”，即list页面中“ FOB Price: US $9-13 / Piece”中的货币单位部分
    private String            currencyType;

    // 货币符号,例如“$”
    private String            currencySymbol;

    // 产品底价，即list页面中“ FOB Price: US $9-13 / Piece”中的价格部分
    private String            lowerPrice;

    // 产品高价
    private String            higherPrice;

    // 产品价格单位
    private String            priceUnit;

    // SEO title
    private String            seotitle;

    // 近半年销量
    private String           saleInSixMonth;

    // 产品的简要描述
    private String            summary;

    // 产品单位 单数
    private String            oddUnitName;

    // 产品单位复数
    private String            multUnitName;
    
    // subject前35个字符
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
