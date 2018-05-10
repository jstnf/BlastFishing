package com.jstnf.blastfishing;

public enum Lang
{
	PREFIX("prefix", "&7[&cBlastFishing&7]");

	private String message, path;

	private Lang(String path, String msg)
	{
		this.path = path;
		this.message = msg;
	}

	public String get()
	{
		String toReturn = this.message;
		if (this == PREFIX)
		{
			toReturn += " ";
		}
		return toReturn;
	}
}
