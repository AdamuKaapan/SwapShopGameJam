package com.swap.particles;

import java.util.ArrayList;

public class ParticleManager {

	public static ArrayList<ParticleManager> particleManagers = new ArrayList<ParticleManager>();
	
	public static void updateParticles(long delta){
		for(ParticleManager manager : particleManagers) manager.update(delta);
	}
	
	private float rotationSpeed, xs, ys, decayTime;
	
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public ParticleManager(){
		
	}
	
	public void update(long delta){
		
	}
	
}
