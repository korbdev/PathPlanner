
public class Content<T> {
	public int id;
	public T content;
	
	public Content(int id, T content){
		this.id = id;
		this.content = content;
	}
	
	/*public boolean equals(Content<T> that){
		if(id == that.id){
			if(content.equals(that.content)){
				return true;
			}
			else return false;
		}
		else return false;
	}*/
	
	public String toString(){
		return Integer.toString(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Content<T> other = (Content<T>) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
