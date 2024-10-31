package com.github.dawsonvilamaa.beaconwaypoint.version;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.EnumDirection;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.EnumHand;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.inventory.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockActionContext;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.entity.TileEntity;
import net.minecraft.world.level.block.entity.TileEntityBeacon;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.phys.MovingObjectPositionBlock;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_21_R2.block.CraftBlock;
import org.bukkit.craftbukkit.v1_21_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Version_1_21_R2 implements VersionWrapper{
    /**
     * Opens the vanilla beacon GUI for a player
     *
     * @param beacon
     * @param player
     */
    @Override
    public void openBeaconMenu(Block beacon, Player player) {
        Location beaconLoc = beacon.getLocation();
        BlockPosition blockPosition = new BlockPosition(beaconLoc.getBlockX(), beaconLoc.getBlockY(), beaconLoc.getBlockZ());
        EntityPlayer playerHandle = ((CraftPlayer) player).getHandle();
        WorldServer levelHandle = playerHandle.y(); //y(): getServerWorld()
        TileEntity beaconTileHandle = levelHandle.getBlockEntity(blockPosition, false);
        Container containerHandle = ((TileEntityBeacon) beaconTileHandle).createMenu(playerHandle.nextContainerCounter(), playerHandle.gi(), playerHandle); //gi(): getInventory()
        playerHandle.a(containerHandle); //a(): initMenu()
        playerHandle.a((TileEntityBeacon) beaconTileHandle); //a(): openMenu()
    }

    /**
     * Returns the blocks available to be used in a beacon pyramid
     *
     * @return
     */
    @Override
    public List<Material> getPyramidBlocks() {
        return Arrays.asList(Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK, Material.NETHERITE_BLOCK);
    }
}
