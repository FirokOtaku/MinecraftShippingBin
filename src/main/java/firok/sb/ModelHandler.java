package firok.sb;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ShippingBin.MOD_ID)
public final class ModelHandler {
	@SubscribeEvent
	public static void onModelReg(ModelRegistryEvent event) {
		Field[] fields=RegistryHandler.class.getFields();
		for(Field field:fields)
		{
			try
			{
				Object obj=field.get(null);
				if(obj instanceof Item && !(obj instanceof ItemBlock))
				{
					Item item=(Item)obj;
					ModelLoader.setCustomModelResourceLocation(
							item,
							0,
							new ModelResourceLocation(item.getRegistryName(),
							"inventory"));
				}
//				else if(obj instanceof Block)
//				{
//					Block block=(Block)obj;
//				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}


	}
}