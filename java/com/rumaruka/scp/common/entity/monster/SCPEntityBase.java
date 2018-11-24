package com.rumaruka.scp.common.entity.monster;

import com.rumaruka.scp.common.entity.SCPEnumCreatureAttribute;
import com.rumaruka.scp.common.entity.ai.SCPFilterAttack;
import com.rumaruka.scp.util.IEntitySelector;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public abstract class SCPEntityBase extends EntityCreature {

    private static final IEntitySelector selector = new SCPFilterAttack();

    public SCPEntityBase(World worldIn) {
        super(worldIn);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);
    }

   public SCPEnumCreatureAttribute getSCPAttribute(){
        return SCPEnumCreatureAttribute.SCP;
   }
}
