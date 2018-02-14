package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> queue;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	if(!rootNode.exists()){
        		throw new FileNotFoundException();
        	}
        	queue = new Queue<File>();
        	queue.enqueue(rootNode);
	}
	
	@Override
	//returns true if the file contains other files or is the current level bring processed is not empty
	public boolean hasNext() {
            return !queue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
		if (!hasNext()) throw new NoSuchElementException();
		File f = queue.dequeue();
		if (f.isDirectory()){
			File[] array = f.listFiles();
			Arrays.sort(array);
			for(int i = 0; i < array.length; i++) queue.enqueue(array[i]);
		}
		return f;

	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();		
	}

}
