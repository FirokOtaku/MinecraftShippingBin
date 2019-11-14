package firok.sb;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.*;
import static firok.sb.Util.*;

public class ItemFoodShippingWand extends Item
{
	public ItemFoodShippingWand()
	{
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ActionResult<ItemStack> result;

		List<Integer> locFoods=new ArrayList<>();
		int totalFoodValue=0;

		InventoryPlayer inv=player.inventory;
		for(int slot=0;slot<inv.getSizeInventory();slot++)
		{
			int foodValue=countFoodValue(inv.getStackInSlot(slot));
			if(foodValue>0)
			{
				locFoods.add(slot);
				totalFoodValue+=foodValue;
			}
		}

		if(totalFoodValue>0)
		{
			for(Integer locFood:locFoods)
			{
				inv.removeStackFromSlot(locFood);
			}
			transformEmeraldAt(player,totalFoodValue,true);

			result=new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		else
		{
			result=new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
		}
		return result;
	}
}
