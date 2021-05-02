package storage;

import exception.NotSupportedException;
import model.Comment;

public class CommentContainer extends Container<Comment> {

	@Override
	public Comment getById(String id) throws NotSupportedException {
		throw new NotSupportedException("Comment container does not supports getById() function");
	}

	@Override
	public Comment getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("Comment container does not supports getById() function");
	}

	@Override
	//modified to write in a json file
	public String toString() {
		String result =  ":["+super.toString();
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		return result+"]";
	}

}
