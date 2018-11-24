package com.rumaruka.scp.common.entity.ai;

import com.google.common.base.Predicate;
import com.rumaruka.scp.util.IEntitySelector;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;


import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class SCPFilterAttack implements IEntitySelector {

    @Override
    public boolean isEntityApplecable(Entity var) {
        return false;
    }
}
