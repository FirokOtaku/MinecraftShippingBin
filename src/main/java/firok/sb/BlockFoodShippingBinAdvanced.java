package firok.sb;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static firok.sb.Util.countFoodValue;
import static firok.sb.Util.transformEmeraldAt;

public class BlockFoodShippingBinAdvanced extends BlockFoodShippingBin
{
	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if(entity instanceof EntityItem)
		{
			EntityItem ei=(EntityItem)entity;
			ItemStack stack=ei.getItem();
			final int foodValue=countFoodValue(stack);

			if(foodValue>0)
			{
				transformEmeraldAt(entity,foodValue,true);
				entity.setDead();
			}
		}
		super.onFallenUpon(world, pos, entity, fallDistance);
	}
}
