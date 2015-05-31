package com.swap.particles;

import java.util.ArrayList;

public class Particle {

	public static ArrayList<Particle> particles = new ArrayList<Particle>();
	
	private float x, y, xs, ys, rotationSpeed, rotation;
	
	public Particle(float x, float y, float xs, float ys, float rotation, float rotationSpeed){
		particles.add(this);
	}
	
	public void update(long delta){
		
	}
	
}
