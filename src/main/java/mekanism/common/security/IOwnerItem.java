package mekanism.common.security;

import net.minecraft.item.ItemStack;

import java.util.UUID;

public interface IOwnerItem {

    UUID getOwnerUUID(ItemStack stack);

    void setOwnerUUID(ItemStack stack, UUID owner);

    boolean hasOwner(ItemStack stack);
}