package ladysnake.illuminations.mixin;

import ladysnake.illuminations.client.IlluminationsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo callbackInfo) {
        // do not render in first person
        if (MinecraftClient.getInstance().gameRenderer.getCamera().isThirdPerson() || MinecraftClient.getInstance().player.getUuid() != this.getUuid()) {
            world.addParticle(IlluminationsClient.FIREFLY_AURA, this.getX(), this.getY() + this.random.nextFloat(), this.getZ(), 0, 0, 0);
        }
    }

}