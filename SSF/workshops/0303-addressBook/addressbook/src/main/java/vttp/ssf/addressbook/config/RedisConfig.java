package vttp.ssf.addressbook.config;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    private Logger logger = Logger.getLogger(RedisConfig.class.getName());

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    // @Value("${spring.redis.password}") 
    // private String redisPassword;

    @Bean
	@Scope("singleton")
	public RedisTemplate<String, Object> redisTemplate() {
		// set up RedisConnection via RedisConnectionFactory
		// using connection to a single node
		final RedisStandaloneConfiguration config = 
			new RedisStandaloneConfiguration();

		logger.log(Level.INFO, "redisHost > " + redisHost + '\n' +
			"redisPort > " + redisPort);

		config.setHostName(redisHost);
		config.setPort(redisPort.get());
		// config.setPassword(redisPassword); // using cloud DB

		// Redis client configuration for Jedis
		final JedisClientConfiguration jedisClient = JedisClientConfiguration
			.builder()
			.build();

		// create Jedis based connections
		final JedisConnectionFactory jedisFac = 
			new JedisConnectionFactory(config, jedisClient);

		// allow bean to perform validation of overall configuration
		// and all final initialisation after properties have been set
		jedisFac.afterPropertiesSet();

		// create a new RedisTemplate instance
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisFac);

		template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(
            getClass().getClassLoader());
        template.setValueSerializer(serializer);

		return template;
    }
}
