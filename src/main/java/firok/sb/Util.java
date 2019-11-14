package firok.sb;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static firok.sb.RegistryHandler.smallEmerald;
import static firok.sb.RegistryHandler.tinyEmerald;

public class Util
{
	public static int countFoodValue(ItemStack stack)
	{
		return (stack!=ItemStack.EMPTY && stack.getItem() instanceof ItemFood)? (stack.getCount()):0;
	}

	public static void spawnEntityItemStack(Entity entity, Item item, int count)
	{
		spawnEntityItemStack(entity.world,entity.posX, entity.posY, entity.posZ, item, count);
	}
	public static void spawnEntityItemStack(World world,double posX,double posY,double posZ, Item item,int count)
	{
		if(item==null||count<=0) return;
		ItemStack stack=new ItemStack(item,count);
		EntityItem ei=new EntityItem(world,posX,posY,posZ,stack);
		world.spawnEntity(ei);
	}

	public static int transformEmeraldAt(Entity entity,int count,boolean transformAll)
	{
		return transformEmeraldAt(entity.world,entity.posX,entity.posY,entity.posZ,count,transformAll);
	}
	public static int transformEmeraldAt(World world,double posX,double posY,double posZ,int count,boolean transformAll)
	{
		if(world.isRemote) return 0;
		final int valueGroup=count/8; // give small
		final int valueRemain=count%8; // give tiny

		int countFinal=count;

		if(transformAll) // 潜行 卖掉所有
		{
			countFinal=0;
			if(valueGroup>0) spawnEntityItemStack(world,posX,posY,posZ,smallEmerald,valueGroup);
			if(valueRemain>0) spawnEntityItemStack(world,posX,posY,posZ,tinyEmerald,valueRemain);
		}
		else // 卖一组
		{
			if(valueGroup>0)
			{
				countFinal-=8;
				spawnEntityItemStack(world,posX,posY,posZ,smallEmerald,1);
			}
			else // valueRemain>0
			{
				countFinal=0;
				spawnEntityItemStack(world,posX,posY,posZ,tinyEmerald,valueRemain);
			}
		}

		// 返回最后还剩下多少
		return countFinal;
	}
}
