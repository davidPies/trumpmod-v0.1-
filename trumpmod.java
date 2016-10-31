package trumpmod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = trumpmod.MODID, version = trumpmod.VERSION)
public class trumpmod
{
    public static final String MODID = "trumpmod";
    public static final String VERSION = "0.1";
    
    	//public static wheatcannon1 wheatcannon1;
        public static Item itemtrumpin;
    	public static Item trumphair2_1;
    	//public static Item hairtest;
    	public static Item money;
    	public static Item snatcher;
    	public static Item spring;
    	public static Item handle;
    	public static Item grabber;
    	public static Item thepoll;
    	public static Item hair2;
    	public static ArmorMaterial HAIR = EnumHelper.addArmorMaterial("trumphair", "trumpmod:trumphair", 10, new int[]{2, 0, 0, 2}, 15, SoundEvents.field_187550_ag, 0.0F);
    	public static ToolMaterial toolmaterial = EnumHelper.addToolMaterial("toolmaterial", 2, 250, 6.0F, 2.0F, 14);
    	@SidedProxy(clientSide="trumpmod.client", serverSide="trumpmod.server")
    public static sharedproxy proxy;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e){
    	proxy.registerRenderers();
    	
    	EntityRegistry.registerModEntity(trump.class, "trump", 0, MODID, 64, 5, true);
    	EntityRegistry.registerEgg(trump.class, 0, 1000);
    	EntityRegistry.addSpawn(trump.class, 1, 1, 2, EnumCreatureType.AMBIENT, Biomes.field_76772_c);
    	EntityRegistry.addSpawn(trump.class, 1, 1, 2, EnumCreatureType.AMBIENT, Biomes.field_180279_ad);
    	
    	int id2 = 122;
    	EntityRegistry.registerModEntity(EntityV.class, "EntityVillager", id2, MODID, 64, 5, true);
    	EntityRegistry.registerEgg(EntityV.class, 0, 150);
    	EntityRegistry.addSpawn(EntityV.class, 1, 0, 1, EnumCreatureType.AMBIENT, Biomes.field_76772_c);
    	
    	int id = 121;
    	EntityRegistry.registerModEntity(trumpinator.class, "trumpinator", id, MODID, 64, 5, true);
    	EntityRegistry.registerEgg(trumpinator.class, 0, 500); 
    	//EntityRegistry.addSpawn(trumpinator.class, 1, 1, 2, EnumCreatureType.AMBIENT, Biomes.PLAINS);
    	 }
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	RenderingRegistry.registerEntityRenderingHandler(trumpinator.class, new RenderBiped(Minecraft.func_71410_x().func_175598_ae(), new ModelBiped(), 1.0F){
    		protected ResourceLocation func_110775_a(Entity entity){
    			return new ResourceLocation("trumpmod", "textures/entity/trumpinator.PNG"); 
    			
    		}}); 
    	RenderingRegistry.registerEntityRenderingHandler(EntityV.class, new RenderBiped(Minecraft.func_71410_x().func_175598_ae(), new ModelBiped(), 1.0F){
    		protected ResourceLocation func_110775_a(Entity entity){
    			return new ResourceLocation("trumpmod", "textures/entity/dealer2.PNG"); 
    			
    		}});
    	RenderingRegistry.registerEntityRenderingHandler(trump.class, new rendercolor(Minecraft.func_71410_x().func_175598_ae(), new ModelBiped(), 1.0F)
    			);
    	//{
    		//protected ResourceLocation getEntityTexture(Entity entity){
    			//return new ResourceLocation("trumpmod", "textures/entity/trumpinator.PNG"); 
    			
    		//}});
    	//EntityRegistry.registerModEntity(trump.class, "trump", 0, MODID, 15, 5, true);
    	//EntityRegistry.registerEgg(trump.class, 0, 1000);
    	GameRegistry.registerItem(trumphair2_1 = new trumphar("trumphair", HAIR, 0, EntityEquipmentSlot.HEAD), "trumphair");
    	//GameRegistry.registerItem(hairtest = new hairtest("trumphair2", HAIR, 0, EntityEquip mentSlot.HEAD), "trumphair2");
    	GameRegistry.registerItem(money = new money(4, 1, false), "money");
    	GameRegistry.registerItem(itemtrumpin = new itemtrumpin(), "itemtrumpin");
    	GameRegistry.registerItem(thepoll = new thepoll("thepoll", toolmaterial), "thepoll");
    	GameRegistry.registerItem(snatcher = new snatcher(), "snatcher");
    	GameRegistry.registerItem(spring = new spring(), "spring");
    	GameRegistry.registerItem(handle = new handle(), "handle");
    	GameRegistry.registerItem(grabber = new grabber(), "grabber");
    	GameRegistry.registerItem(hair2 = new hair2(), "hair2");
    	GameRegistry.addRecipe(new ItemStack(trumpmod.snatcher), new Object[] {"x  ", " z ", "  y", 'x', trumpmod.grabber, 'z', trumpmod.spring, 'y', trumpmod.handle});
    	GameRegistry.addRecipe(new ItemStack(trumpmod.spring, 4), new Object[] {"x  ", " x ", "  x", 'x', Items.field_151007_F});
    	GameRegistry.addRecipe(new ItemStack(trumpmod.handle, 4), new Object[] {"xx", "xx", 'x', Items.field_151055_y});
    	GameRegistry.addRecipe(new ItemStack(trumpmod.grabber, 4), new Object[] {" x", "xx", 'x', Items.field_151055_y});
    	GameRegistry.addRecipe(new ItemStack(trumpmod.trumphair2_1, 1), new Object[] {"xxx", "x x", "   ", 'x', trumpmod.hair2});
    	GameRegistry.addRecipe(new ItemStack(trumpmod.trumphair2_1, 1), new Object[] {"   ", "xxx", "x x", 'x', trumpmod.hair2});
    	
    	//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hairtest, 0, new ModelResourceLocation(MODID + ":" + hairtest.getUnlocalizedName().substring(5), "inventory"));
    	
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(trumphair2_1, 0, new ModelResourceLocation(MODID + ":" + trumphair2_1.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(money, 0, new ModelResourceLocation(MODID + ":" + money.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(snatcher, 0, new ModelResourceLocation(MODID + ":" + snatcher.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(itemtrumpin, 0, new ModelResourceLocation(MODID + ":" + itemtrumpin.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(hair2, 0, new ModelResourceLocation(MODID + ":" + hair2.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(handle, 0, new ModelResourceLocation(MODID + ":" + handle.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(grabber, 0, new ModelResourceLocation(MODID + ":" + grabber.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(spring, 0, new ModelResourceLocation(MODID + ":" + spring.func_77658_a().substring(5), "inventory"));
    	Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(thepoll, 0, new ModelResourceLocation(MODID + ":" + thepoll.func_77658_a().substring(5), "inventory"));
    	
    	//MinecraftForge.EVENT_BUS.register(new haircolor(new rendercolor(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 1.0F)));
    	MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
    	proxy.registerItemColorHandler(trumphair2_1);
    	//ItemColors.registerItemColorHandler(IItemColor.getColorFromItemstack(new ItemStack(trumphair2_1), 2), null); 
    	//Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor(), trumphair2_1);
    	//RenderingRegistry.registerEntityRenderingHandler(entityw.class, new rendersnow(new RenderManager(null, null), 0.0F));
    	//GameRegistry.registerItem(stuff2.cage1(null), "cage1");
        //System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
    }
}
