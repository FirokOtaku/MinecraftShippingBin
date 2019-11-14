package firok.sb;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static firok.sb.Util.*;

public class BlockFoodShippingBin extends Block
{
	public BlockFoodShippingBin()
	{
		super(Material.IRON);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		final boolean isSneaking=player.isSneaking();

		final ItemStack stackHeld=player.getHeldItemMainhand();
		final int foodValue=countFoodValue(stackHeld);

		if(world.isRemote)
		{
			return foodValue>0;
		}
		if(foodValue<=0) return false;

		int countFinal=transformEmeraldAt(player,foodValue,isSneaking);
		stackHeld.setCount(countFinal);

		return foodValue-countFinal>0;
	}
}
