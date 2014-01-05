package me.miclebrick.lumod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Main.MODID, name = "LEGO Universe Mod", version = Main.VERSION)
public class Main {
	public static ToolMaterial EnumPick = net.minecraftforge.common.util.EnumHelper.addToolMaterial("ImaginationPickE", 5, 100000, 20.0F, 7, 10);
	public static ToolMaterial EnumSword = net.minecraftforge.common.util.EnumHelper.addToolMaterial("ImaginationSwordE", 3, 100000, 0.0F, 12, 10);
	 public static final String MODID = "LUMod";
	    public static final String VERSION = "0.0.1";
	    public class ImaginationSword extends ItemSword {
			public ImaginationSword() {
				super(Main.EnumSword);
				setUnlocalizedName("imaginationSword");
                setTextureName(Main.MODID.toLowerCase()+":imaginationSword");
                setCreativeTab(LUModTab);
                setFull3D();
			}			
	    }
	    public class ImaginationPick extends ItemPickaxe {

			protected ImaginationPick() {
				super(Main.EnumPick);
				setUnlocalizedName("imaginationPickaxe");
				setTextureName(Main.MODID.toLowerCase()+":imaginationPickaxe");
				setFull3D();
				setCreativeTab(LUModTab);
			}
	    	
	    }
	    public class ImaginationOrb extends Item {
	    	public ImaginationOrb(){
                maxStackSize = 64;
                setUnlocalizedName("imaginationOrb");
                setTextureName(Main.MODID.toLowerCase()+":imaginationOrb");
                setCreativeTab(LUModTab);
                setFull3D();
	    	}
	    }
	    public CreativeTabs LUModTab = new CreativeTabs("luModTab") {
	    	@Override
            public ItemStack func_151244_d() {
	    		return new ItemStack(imOrb);
	    	}	
	    	
			@Override
			public Item getTabIconItem() {
				return null;
			}
	    	
	    };
	    Item imOrb;
	    Item imSword;
	    Item imPick;
	    @SidedProxy(clientSide="me.miclebrick.lumod.ClientProxy", serverSide="me.miclebrick.lumod.CommonProxy")
	    public static CommonProxy proxy;
	    
	    @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
	    	ItemInit();
        }
	    public void ItemInit(){
	    	imOrb = new ImaginationOrb();
	    	GameRegistry.registerItem(imOrb, "imaginationOrb", MODID);
	    	imSword = new ImaginationSword();
	    	GameRegistry.registerItem(imSword, "imaginationSword", MODID);
	    	imPick = new ImaginationPick();
	    	GameRegistry.registerItem(imPick, "imaginationPickaxe", MODID);
	    }
        @EventHandler
        public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
        	GameRegistry.addShapedRecipe(new ItemStack(imOrb), new Object[]{"DWD", "WGW", "DWD",
        			('D'), Items.diamond, ('W'), Items.water_bucket, ('G'), Items.glowstone_dust});
        	GameRegistry.addShapedRecipe(new ItemStack(imPick), new Object[]{"III", " S ", " S ",
        			('I'), imOrb, ('S'), Items.stick});
        }
}
