package thaumcraftextras.main;

import thaumcraftextras.handlers.FuelHandler;
import thaumcraftextras.main.init.TCEBlocks;
import thaumcraftextras.main.init.TCEEntries;
import thaumcraftextras.main.init.TCEEvents;
import thaumcraftextras.main.init.TCEItems;
import thaumcraftextras.main.init.TCEMisc;
import thaumcraftextras.main.init.TCERecipes;
import thaumcraftextras.main.init.addons.TCEWands;
import thaumcraftextras.proxies.CommonProxy;
import wasliecore.helpers.FileHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ThaumcraftExtras", name = "ThaumcraftExtras", version = "1.0" ,dependencies = "required-after:Thaumcraft;required-after:WaslieCore")
public class ThaumcraftExtras {
    @SidedProxy(clientSide = "thaumcraftextras.proxies.ClientProxy", serverSide = "thaumcraftextras.proxies.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("ThaumcraftExtras")
    public static ThaumcraftExtras instance;
    public static double version = 1.00;
    public static String modName = "ThaumcraftExtras";
    public static String alias = "TCE";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		proxy.load();
		@SuppressWarnings("unused")
		Config config = new Config();
		Config.loadConfig(event);
		FileHelper.createModFolder(modName);
		
    	TCEItems.init();
    	TCEBlocks.init();
    	
    	if(Config.addon_wands){TCEWands.init();};
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
//		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
    	initTiles();
    	TCEEvents.init();
    }
    
    public void initTiles()
    {
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityWardedSpecial.class, "Warded_Ghost");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityShocker.class, "Magic_Shocker");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityDarkendAltar.class, "Darkend_Altar");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityDarkendCore.class, "Darkend_Core");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityDarkendAltarSpecial.class, "Darkend_Altar_Special");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityNoMove.class, "No_Move");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityMagicBattery.class, "Magic_Battery");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityMagicGenerator.class, "Magic_Generator");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityMagicWandCharger.class, "Magic_Wand_Charger");
        GameRegistry.registerTileEntity(thaumcraftextras.api.misc.tiles.MagicEnergyTile.class, "Magic_Tile");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger.class, "Magic_Crystal_Charger");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityMagicVoid.class, "Magic_Energy_Void");
        GameRegistry.registerTileEntity(thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel.class, "Essentia_Barrel");
    }
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
    	TCEMisc.postInit();
    	TCERecipes.init();
    	TCEEntries.init();
    	GameRegistry.registerFuelHandler(new FuelHandler());
    }
}