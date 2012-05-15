package me.tomjw64.HungerBarGames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestClass {
	private String name;
	private List<ChestItem> items=new ArrayList<ChestItem>();
	
	public ChestClass(String className)
	{
		name=className;
	}
	public String getName()
	{
		return name;
	}
	public void fillChest(Chest c)
	{
		Inventory i=c.getInventory();
		int limit=i.getSize();
		for(ChestItem ci:items)
		{
			for(int x=0;x<ci.getAmount();x++)
			{
				if(Math.random()<=ci.getChance())
				{
					int id=ci.getID();
					ItemStack item=new ItemStack(id);
					if(i.contains(id)&&Math.random()<0.8)
					{
						i.addItem(item);
					}
					else
					{
						int index;
						index=(int)(Math.random()*limit);
						if(i.getItem(index).getTypeId()!=0)
						{
							i.setItem(index,item);
						}
						else
						{
							i.addItem(item);
						}
					}
				}
			}
		}
	}
}
