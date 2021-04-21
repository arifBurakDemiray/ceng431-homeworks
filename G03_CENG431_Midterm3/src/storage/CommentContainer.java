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

}
