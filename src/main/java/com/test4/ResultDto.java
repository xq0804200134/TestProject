package com.test4;

public class ResultDto {
	private Integer id;

	// 产品对应的算法
	private String alg;

	// 产品对应的detail页面链接，例如"http://www.alibaba.com/product-gs/244957087/digital_mp3_player_with_FM_radio.html
	private String productUrl;

	// 产品对应的小图，即list页面显示的产品图片，例如“http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.summ.jpg”
	private String summImagePath;

	// 产品对应的中图，即list页面的中图，例如“http://i00.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg_200x200.jpg”
	private String scaleImagePath;
	
	 /**
     * 获取产品默认的图片，即list页面显示的产品图片，例如“http://i03.i.aliimg.com/photo/244957087/digital_mp3_player_with_FM_radio_v8.jpg”
     */
    private String              defaultImagePath;

	// 产品名称
	private String subject;

	// 货币类型,例如“UK,US,RMB”，即list页面中“ FOB Price: US $9-13 / Piece”中的货币单位部分
	private String currencyType;

	// 货币符号,例如“$”
	private String currencySymbol;

	// 产品底价，即list页面中“ FOB Price: US $9-13 / Piece”中的价格部分
	private String lowerPrice;

	// 产品高价
	private String higherPrice;

	// 产品价格单位
	private String priceUnit;
	
	//近三个月销量
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
