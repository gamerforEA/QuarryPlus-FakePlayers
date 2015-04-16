package com.gamerforea.quarryplus;

import java.lang.ref.WeakReference;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import org.bukkit.Bukkit;

import com.gamerforea.wgew.cauldron.event.CauldronBlockBreakEvent;
import com.mojang.authlib.GameProfile;

public final class FakePlayerUtils
{
	private static WeakReference<FakePlayer> player = new WeakReference<FakePlayer>(null);

	private static WeakReference<FakePlayer> createNewPlayer(World world)
	{
		return new WeakReference<FakePlayer>(createFakePlayer(UUID.fromString("a46394de-e45a-11e4-8a00-1681e6b88ec1"), "[QuarryPlus]", world));
	}

	public static final FakePlayer getPlayer(World world)
	{
		if (player.get() == null) player = createNewPlayer(world);
		else player.get().worldObj = world;

		return player.get();
	}

	public static FakePlayer createFakePlayer(UUID uuid, String name, World world)
	{
		return FakePlayerFactory.get((WorldServer) world, new GameProfile(uuid, name));
	}

	public static org.bukkit.event.block.BlockBreakEvent callBlockBreakEvent(int x, int y, int z, EntityPlayer player)
	{
		CauldronBlockBreakEvent event = new CauldronBlockBreakEvent(player, x, y, z);
		Bukkit.getServer().getPluginManager().callEvent(event);
		return event.getBukkitEvent();
	}
}