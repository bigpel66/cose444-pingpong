FROM amazoncorretto:17-alpine3.14-full
ARG ARG_PORT
ARG ARG_REMOTE
ARG ARG_PROFILE
ENV REMOTE ${ARG_REMOTE}
ENV PROFILE ${ARG_PROFILE}
COPY ./pingpong.jar /pingpong.jar
EXPOSE ${ARG_PORT}
CMD [ "sh", "-c", "java -jar -Dspring.profiles.active=${PROFILE} -Dapp.remote=${REMOTE} /pingpong.jar" ]
