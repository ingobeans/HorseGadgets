package org.ingobeans.unneglectedhorses.mixin;

import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractHorseEntity.class)
    public interface AbstractHorseEntityAccessor {
    @Accessor("jumpStrength")
    float getJumpStrength();
    @Accessor("jumpStrength")
    void setJumpStrength(float jumpStrength);
    @Invoker("jump")
    void invokeJump(float strength, Vec3d movementInput);
    }