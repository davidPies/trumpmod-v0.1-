package trumpmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class rendercolor extends RenderLiving<trump>
{
    private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("trumpmod", "textures/entity/trump1_2.png");

    public rendercolor(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
        this.func_177094_a(new haircolor(this));
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void func_76986_a(trump entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation func_110775_a(trump entity)
    {
        return WOLF_TEXTURES;
    }
}
