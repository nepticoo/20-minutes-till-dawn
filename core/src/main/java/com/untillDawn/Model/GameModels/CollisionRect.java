package com.untillDawn.Model.GameModels;

public class CollisionRect {

    public float x, y;
    public float width, height;

    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean intersects(CollisionRect r) {
        float x1 = Math.max(this.x, r.x);
        float y1 = Math.max(this.y, r.y);
        float x2 = Math.min(this.x + this.width, r.x + r.width);
        float y2 = Math.min(this.y + this.height, r.y + r.height);

        float overlapWidth = x2 - x1;
        float overlapHeight = y2 - y1;

        if (overlapWidth <= 0 || overlapHeight <= 0) {
            return false;
        }

        float intersectionArea = overlapWidth * overlapHeight;
        float thisArea = this.width * this.height;
        float rArea = r.width * r.height;

        float minThreshold = 0.2f; // 20% overlap required

        return (intersectionArea / thisArea >= minThreshold) || (intersectionArea / rArea >= minThreshold);
    }

}
