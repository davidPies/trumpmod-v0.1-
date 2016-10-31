package trumpmod;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class trumphar extends ItemArmor{
	public static ArmorMaterial mat;
	public static ArmorMaterial material = EnumHelper.addArmorMaterial("trumphair", "trumpmod:trumphair", 10, new int[]{2, 0, 0, 2}, 15, SoundEvents.field_187550_ag, 0.0F);
	public trumphar(String unlocalizedName, ArmorMaterial mat, int index, EntityEquipmentSlot type) {
	    super(mat, index, type);
	    this.func_77655_b(unlocalizedName);
	    this.material = mat;  
	    this.field_77777_bU = 1;
	    this.func_77637_a(tabs.trumpmodtab);
	    //this.setColor(new ItemStack(trumphar.this), EnumDyeColor.YELLOW.getDyeDamage());
        this.func_77656_e(mat.func_78046_a(type));
        this.func_77637_a(CreativeTabs.field_78037_j);
        this.func_82789_a(new ItemStack(this), new ItemStack(trumpmod.hair2));
	}

	    /**
	     * Return the armor material for this armor item.
	     */
	public boolean func_82816_b_(ItemStack stack)
    {
        if (this.mat != material)
        {
            return false;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.func_77978_p();
            return nbttagcompound != null && nbttagcompound.func_150297_b("display", 10) ? nbttagcompound.func_74775_l("display").func_150297_b("color", 3) : false;
        }
    }

    /**
     * Return the color for the specified armor ItemStack.
     */
    public int func_82814_b(ItemStack stack)
    {
        if (this.mat != material)
        {
            return 16777215;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.func_77978_p();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

                if (nbttagcompound1 != null && nbttagcompound1.func_150297_b("color", 3))
                {
                    return nbttagcompound1.func_74762_e("color");
                }
            }

            return 10511680;
        }
    }

    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void func_82815_c(ItemStack stack)
    {
        if (this.mat == material)
        {
            NBTTagCompound nbttagcompound = stack.func_77978_p();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

                if (nbttagcompound1.func_74764_b("color"))
                {
                    nbttagcompound1.func_82580_o("color");
                }
            }
        }
    }

    /**
     * Sets the color of the specified armor ItemStack
     */
    public void func_82813_b(ItemStack stack, int color)
    {
        if (this.mat != material)
        {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.func_77978_p();

            if (nbttagcompound == null)
            {
                nbttagcompound = new NBTTagCompound();
                stack.func_77982_d(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

            if (!nbttagcompound.func_150297_b("display", 10))
            {
                nbttagcompound.func_74782_a("display", nbttagcompound1);
            }

            nbttagcompound1.func_74768_a("color", color);
        }
    }
}
