package de.adesso.anki.messages;

import java.nio.ByteBuffer;

import com.google.common.base.MoreObjects;

/**
 * Notifies the controller that the vehicle left the current roadpiece.
 * This message is sent every time the vehicle passes a transition bar.
 * 
 * @author Yannick Eckey <yannick.eckey@adesso.de>
 */
public class LocalizationTransitionUpdateMessage extends Message {
  public static final int TYPE = 0x29;
  
  private int roadPieceId; // signed byte
  private int prevRoadPieceId; // signed byte
  private float offsetFromRoadCenter; // float

  private int lastReceivedLaneChangeId; // unsigned byte
  private int lastExecutedLaneChangeId; // unsigned byte
  private int lastDesiredLangeChangeSpeed; // unsigned short
  private int aveFollowLineDriftPixels; // unsigned short
  private int hadLaneChangeActivity;
  
  private int uphillCounter; // unsigned byte
  private int downhillCounter; // unsigned byte
  
  private int leftWheelDistance; // unsigned byte
  private int rightWheelDistance; // unsigned byte
  
  public LocalizationTransitionUpdateMessage() {
    this.type = TYPE;
    
    // ...
  }
  
  @Override
  protected void parsePayload(ByteBuffer buffer) {  
    this.roadPieceId = buffer.get();
    this.prevRoadPieceId = buffer.get();
    this.offsetFromRoadCenter = buffer.getFloat();
    
    this.lastReceivedLaneChangeId = Byte.toUnsignedInt(buffer.get());
    this.lastExecutedLaneChangeId = Byte.toUnsignedInt(buffer.get());
    this.lastDesiredLangeChangeSpeed = Short.toUnsignedInt(buffer.getShort());
    this.aveFollowLineDriftPixels = Byte.toUnsignedInt(buffer.get());
    this.hadLaneChangeActivity = Byte.toUnsignedInt(buffer.get());
    
    this.uphillCounter = Byte.toUnsignedInt(buffer.get());
    this.downhillCounter = Byte.toUnsignedInt(buffer.get());

    this.leftWheelDistance = Byte.toUnsignedInt(buffer.get());
    this.rightWheelDistance = Byte.toUnsignedInt(buffer.get());
  }
  
  @Override
  protected void preparePayload(ByteBuffer buffer) {
    buffer.put((byte) this.roadPieceId);
    buffer.put((byte) this.prevRoadPieceId);
    buffer.putFloat(this.offsetFromRoadCenter);

    buffer.put((byte) this.lastReceivedLaneChangeId);
    buffer.put((byte) this.lastExecutedLaneChangeId);
    buffer.putShort((short) this.lastDesiredLangeChangeSpeed);
    buffer.put((byte) this.aveFollowLineDriftPixels);
    buffer.put((byte) this.hadLaneChangeActivity);

    buffer.put((byte) this.uphillCounter);
    buffer.put((byte) this.downhillCounter);
    
    buffer.put((byte) this.leftWheelDistance);
    buffer.put((byte) this.rightWheelDistance);
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("offset", this.offsetFromRoadCenter)
        .add("leftWheelDistance", this.leftWheelDistance)
        .add("rightWheelDistance", this.rightWheelDistance)
        .toString();
  }

  public int getRoadPieceId() {
    return roadPieceId;
  }
  
  public int getHadLaneChangeActivity() {
	  return hadLaneChangeActivity;
  }

  public int getPrevRoadPieceId() {
    return prevRoadPieceId;
  }

  public float getOffsetFromRoadCenter() {
    return offsetFromRoadCenter;
  }

  public int getLastReceivedLaneChangeId() {
    return lastReceivedLaneChangeId;
  }

  public int getLastExecutedLaneChangeId() {
    return lastExecutedLaneChangeId;
  }

  public int getLastDesiredLangeChangeSpeed() {
    return lastDesiredLangeChangeSpeed;
  }

  public int getAveFollowLineDriftPixels() {
    return aveFollowLineDriftPixels;
  }

  public int getUphillCounter() {
    return uphillCounter;
  }

  public int getDownhillCounter() {
    return downhillCounter;
  }

  public int getLeftWheelDistance() {
    return leftWheelDistance;
  }

  public int getRightWheelDistance() {
    return rightWheelDistance;
  }
  
}
