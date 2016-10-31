package trumpmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.fml.relauncher.Side;

public class client extends sharedproxy{
	public final ItemColors itemColor = FMLClientHandler.instance().getClient().getItemColors();
	@EventHandler
    public void pre(FMLPreInitializationEvent e){
		super.pre(e);
		
    }
	@EventHandler
    public void init(FMLInitializationEvent e){
		super.init(e);
    }
	public void registerRenderers() {
		//problem binding texture
		//RenderingRegistry.registerEntityRenderingHandler(trump.class, new RenderLiving(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 1.0F){
		//protected ResourceLocation getEntityTexture(Entity entity){
			//return new ResourceLocation("trumpmod:texures/entity/steve.png");
			
		//}
		//});
	}
	@Override 
    public void registerItemColorHandler(Item item){
        if (item instanceof IItemColor){
            IItemColor iItemColor = (IItemColor)item;
            itemColor.func_186730_a(iItemColor,item);
        }
    }
}
