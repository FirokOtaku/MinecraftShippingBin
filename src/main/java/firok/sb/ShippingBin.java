package firok.sb;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
		modid = ShippingBin.MOD_ID,
		name = ShippingBin.MOD_NAME,
		version = ShippingBin.VERSION
)
public class ShippingBin
{

	public static final String MOD_ID = "sb";
	public static final String MOD_NAME = "ShippingBin";
	public static final String VERSION = "0.9.0";

	@Mod.Instance(MOD_ID)
	public static ShippingBin INSTANCE;

	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		;
	}

	/**
	 * This is the second initialization event. Register custom recipes
	 */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{

	}

}
