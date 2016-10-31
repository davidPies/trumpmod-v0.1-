package trumpmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class itemtrumpin extends Item
{
    public itemtrumpin()
    {
        this.field_77777_bU = 16;
        this.func_77637_a(tabs.trumpmodtab);
        this.func_77655_b("itemtrumpin");
    }

    public ActionResult<ItemStack> func_77659_a(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!playerIn.field_71075_bZ.field_75098_d)
        {
            --itemStackIn.field_77994_a;
        }

        worldIn.func_184148_a((EntityPlayer)null, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundEvents.field_187511_aA, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.field_72995_K)
        {
            trumpinatoregg entityegg = new trumpinatoregg(worldIn, playerIn);
            entityegg.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0F, 1.5F, 1.0F);
            worldIn.func_72838_d(entityegg);
        }

        playerIn.func_71029_a(StatList.func_188057_b(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}
