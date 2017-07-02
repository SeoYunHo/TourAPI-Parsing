package com.yunho.sigmungo.parser.support;

public enum ParserBase {
	DEF_PARAM(
			"?areaCode=3&ServiceKey=bb%2FPPi9Iy9rNdmIN7PIdb4doQ8PCwL725OFZndZ7DS%2FbP8%2Bzr9T3rpoD%2B083JYDwg5YJyi3HQ3UZ5%2Fp0e6ER8Q%3D%3D&MobileOS=AND&MobileApp=DaejeonPeople&_type=json");
	private final String name;

	private ParserBase(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
