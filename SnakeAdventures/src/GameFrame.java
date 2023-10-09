import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame  {

	static Graphics2D mGraphics;

	GameFrame() { 
	
		this.add(new SnakeAdventures());
		this.setTitle("Snake Adventures Final Release");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // our close method
		this.setResizable(false); //
		this.pack(); //when adding components to JFrame it will make them pack together.
		this.setVisible(true);
		this.setLocationRelativeTo(null); //This makes the game appear in the center of the screen

	}
	
	public static Image loadImage(String filename) {
		try {
			// Load Image
			Image image = ImageIO.read(new File(filename));

			// Return Image
			return image;
		} catch (IOException e) {
			// Show Error Message
			System.out.println("Error: could not load image " + filename);
			System.exit(1);
		}

		// Return null
		return null;
	}
	
	public static void drawImage(Image image, double x, double y) {
		// Check if image is null
		if(image == null) {
			// Print Error message
			System.out.println("Error: cannot draw null image.\n");
			return;
		}

		// Draw image on screen at (x,y)
		mGraphics.drawImage(image, (int)x, (int)y, null);
	}

	// Draws an image on the screen at position (x,y)
	public static void drawImage(Image image, double x, double y, double w, double h) {
		// Check if image is null
		if(image == null) {
			// Print Error message
			System.out.println("Error: cannot draw null image.\n");
			return;
		}
		// Draw image on screen at (x,y) with size (w,h)
		mGraphics.drawImage(image, (int)x, (int)y, (int)w, (int)h, null);
	}
	
	  // Class used to store an audio clip
			public static class AudioClip {
				// Format
				AudioFormat mFormat;

				// Audio Data
				byte[] mData;

				// Buffer Length
				long mLength;

				// Loop Clip
				Clip mLoopClip;

				public Clip getLoopClip() {
					// return mLoopClip
					return mLoopClip;
				}

				public void setLoopClip(Clip clip) {
					// Set mLoopClip to clip
					mLoopClip = clip;
				}

				public AudioFormat getAudioFormat() {
					// Return mFormat
					return mFormat;
				}

				public byte[] getData() {
					// Return mData
					return mData;
				}

				public long getBufferSize() {
					// Return mLength
					return mLength;
				}

				public AudioClip(AudioInputStream stream) {
					// Get Format
					mFormat = stream.getFormat();

					// Get length (in Frames)
					mLength = stream.getFrameLength() * mFormat.getFrameSize();

					// Allocate Buffer Data
					mData = new byte[(int)mLength];

					try {
						// Read data
						stream.read(mData);
					} catch(Exception exception) {
						// Print Error
						System.out.println("Error reading Audio File\n");

						// Exit
						System.exit(1);
					}

					// Set LoopClip to null
					mLoopClip = null;
				}
			}

			// Loads the AudioClip stored in the file specified by filename
			public static AudioClip loadAudio(String filename) {
				try {
					// Open File
					File file = new File(filename);

					// Open Audio Input Stream
					AudioInputStream audio = AudioSystem.getAudioInputStream(file);

					// Create Audio Clip
					AudioClip clip = new AudioClip(audio);

					// Return Audio Clip
					return clip;
				} catch(Exception e) {
					// Catch Exception
					System.out.println("Error: cannot open Audio File " + filename + "\n");
				}

				// Return Null
				return null;
			}
				
			public void stopAudio(AudioClip audioClip) {
				if(audioClip == null) {
					// Print error message
					System.out.println("Error: audioClip is null\n");

					// Return
					return;
				}

				try {
					// Create a Clip
					Clip clip = AudioSystem.getClip();
					//Clip clip;

					// Load data
					//clip.open(audioClip.getAudioFormat(), audioClip.getData(), 0, (int)audioClip.getBufferSize());

					// Play Clip
					clip.stop();
					//Thread.sleep( 2500 ); //Could use a timer here instead
				} catch(Exception exception) {
					// Display Error Message
					System.out.println("Error playing Audio Clip\n");
				}
			}
			
			
			// Plays an AudioClip
			public static void playAudio(AudioClip audioClip) {
				// Check audioClip for null
				if(audioClip == null) {
					// Print error message
					System.out.println("Error: audioClip is null\n");

					// Return
					return;
				}

				try {
					// Create a Clip
					Clip clip = AudioSystem.getClip();

					// Load data
					clip.open(audioClip.getAudioFormat(), audioClip.getData(), 0, (int)audioClip.getBufferSize());

					// Play Clip
					clip.start();
					
					//if (State == STATE.GAMEOVER) {
						//System.out.println("Game has ended");
						//clip.stop();
					//}
					
					//Thread.sleep( 2500 ); //Could use a timer here instead
				} catch(Exception exception) {
					// Display Error Message
					System.out.println("Error playing Audio Clip\n");
				}
				
			//	clip.stop();
			}

			// Plays an AudioClip with a volume in decibels
			public static void playAudio(AudioClip audioClip, float volume) {
				// Check audioClip for null
				if(audioClip == null) {
					// Print error message
					System.out.println("Error: audioClip is null\n");

					// Return
					return;
				}

				try {
					// Create a Clip
					Clip clip = AudioSystem.getClip();

					// Load data
					clip.open(audioClip.getAudioFormat(), audioClip.getData(), 0, (int)audioClip.getBufferSize());

					// Create Controls
					FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

					// Set Volume
					control.setValue(volume);

					// Play Clip
					clip.start();
					//Thread.sleep( 2500 ); //Could use a timer here instead
				} catch(Exception exception) {
					// Display Error Message
					System.out.println("Error: could not play Audio Clip\n");
				}
			}

			//public void stop()
			//  {
			  //  audioclip.player.stop(audio);
			//  }
			
			
			// Starts playing an AudioClip on loop
			public void startAudioLoop(AudioClip audioClip) {
				// Check audioClip for null
				if(audioClip == null) {
					// Print error message
					System.out.println("Error: audioClip is null\n");

					// Return
					return;
				}

				// Get Loop Clip
				Clip clip = audioClip.getLoopClip();

				// Create Loop Clip if necessary
				if(clip == null) {
					try {
						// Create a Clip
						clip = AudioSystem.getClip();

						// Load data
						clip.open(audioClip.getAudioFormat(), audioClip.getData(), 0, (int)audioClip.getBufferSize());

						// Set Clip to Loop
						clip.loop(Clip.LOOP_CONTINUOUSLY);

						// Set Loop Clip
						audioClip.setLoopClip(clip);
					} catch(Exception exception) {
						// Display Error Message
						System.out.println("Error: could not play Audio Clip\n");
					}
				}

				// Set Frame Position to 0
				clip.setFramePosition(0);

				// Start Audio Clip playing
				clip.start();
			}

			// Starts playing an AudioClip on loop with a volume in decibels
			public static void startAudioLoop(AudioClip audioClip, float volume) {
				// Check audioClip for null
				if(audioClip == null) {
					// Print error message
					System.out.println("Error: audioClip is null\n");

					// Return
					return;
				}

				// Get Loop Clip
				Clip clip = audioClip.getLoopClip();

				// Create Loop Clip if necessary
				if(clip == null) {
					try {
						// Create a Clip
						clip = AudioSystem.getClip();

						// Load data
						clip.open(audioClip.getAudioFormat(), audioClip.getData(), 0, (int)audioClip.getBufferSize());

						// Create Controls
						FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

						// Set Volume
						control.setValue(volume);

						// Set Clip to Loop
						clip.loop(Clip.LOOP_CONTINUOUSLY);

						// Set Loop Clip
						audioClip.setLoopClip(clip);
					} catch(Exception exception) {
						// Display Error Message
						System.out.println("Error: could not play Audio Clip\n");
					}
				}

				// Set Frame Position to 0
				clip.setFramePosition(0);

				// Start Audio Clip playing
				clip.start();
			}

			// Stops an AudioClip playing
			public static void stopAudioLoop(AudioClip audioClip) {
				// Get Loop Clip
				Clip clip = audioClip.getLoopClip();

				// Check clip is not null
				if(clip != null){
					// Stop Clip playing
					clip.stop();
				}
			} 
	
	public static void test() {
		System.out.println("Called in GameFrame");
	}
	
}
