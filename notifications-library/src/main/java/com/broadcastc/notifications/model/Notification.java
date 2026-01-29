package com.broadcastc.notifications.model;

import java.util.HashMap;
import java.util.Map;

public record Notification(String id,
		String from,
		String to,
		String content,
		Map<String,Object> metadata) {
	

	
	    public static Builder builder() {
	        return new Builder();
	    }

	    public static final class Builder {

	        private String id;
	        private String from;
	        private String to;
	        private String content;
	        private Map<String,Object> metadata; 

	        private Builder() {}

	        public Builder id(String id) {
	            this.id = id;
	            return this;
	        }

	        public Builder from(String from) {
	            this.from = from;
	            return this;
	        }

	        public Builder to(String to) {
	            this.to = to;
	            return this;
	        }

	        public Builder content(String content) {
	            this.content = content;
	            return this;
	        }

	    
	        public Builder addMetadata(String key, Object value) {
	        	if (this.metadata==null) {
	        		this.metadata = new HashMap<>();
	        	}
	        	
	            metadata.put(key, value);
	            return this;
	        }
	        

	        public Notification build() {
	            return new Notification(
	                    id,
	                    from,
	                    to,
	                    content,
	                    metadata
	            );
	        }
	    }
}
