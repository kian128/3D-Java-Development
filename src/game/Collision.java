package game;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import render.VoxelRendering;

import entity.Entity;
import entity.EntityList;
import entity.EntityPlayer;

public class Collision {
	
	public static EntityPlayer player = new EntityPlayer();
	
 	public static boolean playerIsCollidingWithEntity(EntityPlayer entity1, Entity entity2) {
		if(entity1.getX() + entity1.getWidth() > entity2.getX() && entity1.getX() < entity2.getX() + entity2.getWidth()) {
    		if(entity1.getY() + entity1.getHeight() > entity2.getY() && entity1.getY() < entity2.getY() + entity2.getHeight()) {
    			if(entity1.getZ() + entity1.getWidth() > entity2.getZ() && entity1.getZ() < entity2.getZ() + entity2.getWidth()) {
    				//return true;
            	}
        	}
    	}
 		return false;
	}
 	
 	public static boolean checkPlayerCollision(float x, float y, float z) {
 		Entity activeCube = EntityList.cubes.get(0);
        
		for(int entity = 0; entity < EntityList.cubes.size(); entity++) {
		   	Entity cube = EntityList.cubes.get(entity);
		    if(isCollidingWithCube(x, y, z, player.getWidth(), player.getHeight(), cube)) {
		    	activeCube = cube;
		    }
		}
		
		if(isCollidingWithCube(x, y, z, player.getWidth(), player.getHeight(), activeCube)) {
	    	return true;
	    }
		return false;
 	}
 	
 	public static boolean isCollidingWithCube(float x, float y, float z, float width, float height, Entity cube) {
 		if(x + width > cube.getX() && x < cube.getX() + cube.getWidth()) {
    		if(y + height > cube.getY() && y < cube.getY() + cube.getHeight()) {
    			if(z + width > cube.getZ() && z < cube.getZ() + cube.getWidth()) {
    				return true;
            	}
        	}
    	}
		return false;
 	}
}