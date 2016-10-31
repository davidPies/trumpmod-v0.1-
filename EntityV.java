package trumpmod;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityV extends EntityCow {

	public EntityV(World worldIn) {
		super(worldIn);
	}
	protected SoundEvent func_184639_G()
    {
        return SoundEvents.field_187910_gj;
    }

    protected SoundEvent func_184601_bQ()
    {
        return SoundEvents.field_187800_eb;
    }

    protected SoundEvent func_184615_bR()
    {
        return SoundEvents.field_187798_ea;
    }

    protected void func_180429_a(BlockPos pos, Block blockIn)
    {
        this.func_184185_a(SoundEvents.field_187765_eK, 0.15F, 1.0F);
    }
	public boolean func_184645_a(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack)
    {
        ItemStack itemstack = player.field_71071_by.func_70448_g();
        if(stack !=null){
        if(stack.func_77973_b() == trumpmod.money)
        {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(trumpmod.itemtrumpin));
            return true;
        }}
		return super.func_184645_a(player, hand, stack);
    }
}
