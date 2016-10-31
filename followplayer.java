package trumpmod;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class followplayer extends EntityAIBase
{
    private final trumpinator thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private final double followSpeed;
    private final PathNavigate petPathfinder;
    private int timeToRecalcPath;
    float maxDist;
    float minDist;
    private float oldWaterCost;

    public followplayer(trumpinator thePetIn, double followSpeedIn, float minDistIn, float maxDistIn)
    {
        this.thePet = thePetIn;
        this.theWorld = thePetIn.field_70170_p;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = thePetIn.func_70661_as();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.func_75248_a(3);

        if (!(thePetIn.func_70661_as() instanceof PathNavigateGround))
        {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean func_75250_a()
    {
        EntityLivingBase entitylivingbase = this.thePet;

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).func_175149_v())
        {
            return false;
        }
        else if (this.thePet.func_70068_e(entitylivingbase) < (double)(this.minDist * this.minDist))
        {
            return false;
        }
        else
        {
            this.theOwner = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean func_75253_b()
    {
        return !this.petPathfinder.func_75500_f() && this.thePet.func_70068_e(this.theOwner) > (double)(this.maxDist * this.maxDist);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void func_75249_e()
    {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.thePet.func_184643_a(PathNodeType.WATER);
        this.thePet.func_184644_a(PathNodeType.WATER, 0.0F);
    }

    /**
     * Resets the task
     */
    public void func_75251_c()
    {
        this.theOwner = null;
        this.petPathfinder.func_75499_g();
        this.thePet.func_184644_a(PathNodeType.WATER, this.oldWaterCost);
    }

    private boolean isEmptyBlock(BlockPos pos)
    {
        IBlockState iblockstate = this.theWorld.func_180495_p(pos);
        return iblockstate.func_185904_a() == Material.field_151579_a ? true : !iblockstate.func_185917_h();
    }

    /**
     * Updates the task
     */
    public void func_75246_d()
    {
        this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0F, (float)this.thePet.func_70646_bf());

            if (--this.timeToRecalcPath <= 0)
            {
                this.timeToRecalcPath = 10;

                if (!this.petPathfinder.func_75497_a(this.theOwner, this.followSpeed))
                {
                    if (!this.thePet.func_110167_bD())
                    {
                        if (this.thePet.func_70068_e(this.theOwner) >= 144.0D)
                        {
                            int i = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
                            int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
                            int k = MathHelper.func_76128_c(this.theOwner.func_174813_aQ().field_72338_b);

                            for (int l = 0; l <= 4; ++l)
                            {
                                for (int i1 = 0; i1 <= 4; ++i1)
                                {
                                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.theWorld.func_180495_p(new BlockPos(i + l, k - 1, j + i1)).func_185896_q() && this.isEmptyBlock(new BlockPos(i + l, k, j + i1)) && this.isEmptyBlock(new BlockPos(i + l, k + 1, j + i1)))
                                    {
                                        this.thePet.func_70012_b((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.thePet.field_70177_z, this.thePet.field_70125_A);
                                        this.petPathfinder.func_75499_g();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
}
