package de.siphalor.nbtcrafting;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.fabricmc.api.ModInitializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sortme.JsonLikeTagParser;

public class Core implements ModInitializer {
	
	public static final String JSON_NBT_KEY = "data";
	
	private static boolean lastReadNbtPresent = false;
	private static CompoundTag lastReadNbt;
	
	public static boolean hasLastReadNbt() {
		return lastReadNbtPresent;
	}
	
	public static void setLastReadNbt(CompoundTag nbt) {
		lastReadNbt = nbt;
		lastReadNbtPresent = true;
	}
	
	public static CompoundTag useLastReadNbt() {
		CompoundTag result = null;
		if(lastReadNbt != null) {
			result = lastReadNbt.copy();
			lastReadNbt = null;
		}
		lastReadNbtPresent = false;
		return result;
	}
	
	public static CompoundTag parseNbtString(String string) {
		try {
			return new JsonLikeTagParser(new StringReader(string)).parseCompoundTag();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void onInitialize() {
	}

}
