package com.rumaruka.scp.common.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class SCPEntityAttributes extends SharedMonsterAttributes {

    public static final IAttribute MAX_BLINK = new RangedAttribute(null,"scp.max_blink",400.0d,0.0d,Float.MAX_VALUE);
    public static final IAttribute BLINK = new RangedAttribute(null,"scp.blink",30.0d,0.0d,Float.MAX_VALUE);
    public static final IAttribute BLINKING = new RangedAttribute(null,"scp.blinking",31.0d,0.0d,Float.MAX_VALUE);


    public static class GlobasEntity {
        public static float FALSE = 0.0f;
        public static float TRUE = 1.0f;
    }
}
