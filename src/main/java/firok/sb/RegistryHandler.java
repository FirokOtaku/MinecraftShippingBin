package firok.sb;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = ShippingBin.MOD_ID)
public class RegistryHandler
{
	@RegName(Texture = "food_shipping_bin",Translate = "food_shipping_bin")
	public static final BlockFoodShippingBin foodShippingBin=new BlockFoodShippingBin();

	@RegName(Texture = "food_shipping_bin_advanced",Translate = "food_shipping_bin_advanced")
	public static final BlockFoodShippingBin foodShippingBinAdvanced=new BlockFoodShippingBinAdvanced();

	@RegName(Texture = "food_shipping_wand", Translate = "food_shipping_wand")
	public static final Item foodShippingWand=new ItemFoodShippingWand();

	@RegName(Texture = "small_emerald",Translate = "small_emerald")
	public static final Item smallEmerald=new Item();
	@RegName(Texture = "tiny_emerald",Translate = "tiny_emerald")
	public static final Item tinyEmerald=new Item();

	@SubscribeEvent
	public static void onRegItems(RegistryEvent.Register<Item> event)
	{
		ItemRegistryHelper helper=new ItemRegistryHelper(event.getRegistry());

		for(Field field: RegistryHandler.class.getFields())
		{
			try
			{
				Object obj=field.get(null);
				if(!(obj instanceof Block)) continue;
				Block block=(Block)obj;

				RegName annoField=field.getAnnotation(RegName.class);
				if(annoField!=null)
				{
					String nameRegistry=annoField.Translate();
					String nameTexture=annoField.Texture();

					helper.reg(new ItemBlock(block),nameTexture,nameRegistry);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		for(Field field: RegistryHandler.class.getFields())
		{
			try
			{
				Object obj=field.get(null);
				if(!(obj instanceof Item)) continue;
				Item item=(Item)field.get(null);

				RegName annoField=field.getAnnotation(RegName.class);
				if(annoField!=null)
				{
					String nameRegistry=annoField.Translate();
					String nameTexture=annoField.Texture();

					helper.reg(item,nameTexture,nameRegistry);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public static void onRegBlocks(RegistryEvent.Register<Block> event)
	{
		BlockRegistryHelper helper=new BlockRegistryHelper(event.getRegistry());

		for(Field field: RegistryHandler.class.getFields())
		{
			try
			{
				Object obj=field.get(null);
				if(!(obj instanceof Block)) continue;;
				Block block=(Block)field.get(null);

				RegName annoField=field.getAnnotation(RegName.class);
				if(annoField!=null)
				{
					String nameRegistry=annoField.Translate();
					String nameTexture=annoField.Texture();

					helper.reg(block,nameTexture,nameRegistry);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static class ItemRegistryHelper
	{
		private IForgeRegistry<Item> registry;
		public ItemRegistryHelper(IForgeRegistry<Item> registry)
		{
			this.registry=registry;
		}

		public void reg(Item item,String nameTexture,String nameTranslate)
		{
			item.setTranslationKey("sb."+nameTranslate);
			item.setRegistryName(ShippingBin.MOD_ID, nameTexture);
			item.setCreativeTab(CreativeTabs.MATERIALS);

			registry.register(item);
		}
	}

	private static class BlockRegistryHelper
	{
		private IForgeRegistry<Block> registry;
		public BlockRegistryHelper(IForgeRegistry<Block> registry)
		{
			this.registry=registry;
		}

		public void reg(Block block,String nameTexture,String nameTranslate)
		{
			block.setTranslationKey("sb."+nameTranslate);
			block.setRegistryName(ShippingBin.MOD_ID,nameTexture);
			block.setCreativeTab(CreativeTabs.DECORATIONS);

			registry.register(block);
		}
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface RegName
	{
		String Texture();
		String Translate();
	}
}
