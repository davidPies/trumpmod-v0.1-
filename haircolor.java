package trumpmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class haircolor implements LayerRenderer<trump>
{
    private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("trumpmod", "textures/entity/haircolor.png");
    private final rendercolor wolfRenderer;

    public haircolor(rendercolor rendercolor)
    {
        this.wolfRenderer = rendercolor;
    }

    public void func_177141_a(trump entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
            this.wolfRenderer.func_110776_a(WOLF_COLLAR);
            EnumDyeColor enumdyecolor = EnumDyeColor.func_176764_b(entitylivingbaseIn.getFleeceColor().func_176765_a());
            float[] afloat = trump.getDyeRgb(enumdyecolor);
            GlStateManager.func_179124_c(afloat[0], afloat[1], afloat[2]);
            this.wolfRenderer.func_177087_b().func_78088_a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public boolean func_177142_b()
    {
        return true;
    }
}
