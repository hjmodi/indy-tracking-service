/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy-repository-service)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.service.tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.commonjava.indy.service.tracking.model.pkg.MavenPackageTypeDescriptor;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.MalformedURLException;
import java.net.URL;

@Schema( description = "Proxy to a remote server's artifact content, with local cache storage.", type = SchemaType.OBJECT )
public class RemoteRepository
                extends AbstractRepository
                implements Externalizable
{
    public static final String PREFETCH_LISTING_TYPE_HTML = "html";

    public static final int DEFAULT_MAX_CONNECTIONS = 30;

    private static final int STORE_VERSION = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger( RemoteRepository.class );

    @JsonProperty( "url" )
    @Schema( description = "The remote URL to proxy", required = true )
    private String url;

    @JsonProperty( "timeout_seconds" )
    private int timeoutSeconds;

    @JsonProperty( "max_connections" )
    private int maxConnections = DEFAULT_MAX_CONNECTIONS;

    @JsonProperty( "ignore_hostname_verification" )
    private boolean ignoreHostnameVerification;

    @JsonProperty( "nfc_timeout_seconds" )
    private int nfcTimeoutSeconds;

    private String host;

    private int port;

    private String user;

    private String password;

    @JsonProperty( "is_passthrough" )
    private boolean passthrough;

    @JsonProperty( "cache_timeout_seconds" )
    private int cacheTimeoutSeconds;

    @JsonProperty( "metadata_timeout_seconds" )
    private int metadataTimeoutSeconds;

    @JsonProperty( "key_password" )
    private String keyPassword;

    @JsonProperty( "key_certificate_pem" )
    private String keyCertificatePem;

    @JsonProperty( "server_certificate_pem" )
    private String serverCertificatePem;

    @JsonProperty( "proxy_host" )
    private String proxyHost;

    @JsonProperty( "proxy_port" )
    private int proxyPort;

    @JsonProperty( "proxy_user" )
    private String proxyUser;

    @JsonProperty( "proxy_password" )
    private String proxyPassword;

    @JsonProperty( "server_trust_policy" )
    private String serverTrustPolicy;

    @Schema( description = "Integer to indicate the pre-fetching priority of the remote, higher means more eager to do the pre-fetching of the content in the repo, 0 or below means disable the pre-fecthing." )
    @JsonProperty( "prefetch_priority" )
    private Integer prefetchPriority = 0;

    @Schema( description = "Indicates if the remote needs to do rescan after prefetch" )
    @JsonProperty( "prefetch_rescan" )
    private boolean prefetchRescan = false;

    @Schema( description = "The prefetch listing type, should be html or koji" )
    @JsonProperty( "prefetch_listing_type" )
    private String prefetchListingType = PREFETCH_LISTING_TYPE_HTML;

    @JsonProperty( "prefetch_rescan_time" )
    private String prefetchRescanTimestamp;

    public RemoteRepository()
    {
        super();
    }

    @Deprecated
    public RemoteRepository( final String name, final String remoteUrl )
    {
        this( MavenPackageTypeDescriptor.MAVEN_PKG_KEY, name, remoteUrl );
    }

    public RemoteRepository( final String packageType, String name, String remoteUrl )
    {
        super( packageType, StoreType.remote, name );
        this.url = remoteUrl;
        calculateFields();
    }

    public String getUrl()
    {
        calculateIfNeeded();
        return url;
    }

    public void setUrl( final String url )
    {
        this.url = url;
        calculateFields();
    }

    public String getUser()
    {
        calculateIfNeeded();
        return user;
    }

    public void setUser( final String user )
    {
        this.user = user;
    }

    private void calculateIfNeeded()
    {
        if ( host == null )
        {
            calculateFields();
        }
    }

    public String getPassword()
    {
        calculateIfNeeded();
        return password;
    }

    public void setPassword( final String password )
    {
        this.password = password;
    }

    public String getHost()
    {
        calculateIfNeeded();
        return host;
    }

    public void setHost( final String host )
    {
        this.host = host;
    }

    public int getPort()
    {
        calculateIfNeeded();
        return port;
    }

    public void setPort( final int port )
    {
        this.port = port;
    }

    public void calculateFields()
    {
        URL url = null;
        try
        {
            url = new URL( this.url );
        }
        catch ( final MalformedURLException e )
        {
            LOGGER.warn( String.format( "Failed to parse repository URL: '%s'. Reason: %s", this.url,
                                        e.getMessage() ) );
        }

        if ( url == null )
        {
            return;
        }

        final String userInfo = url.getUserInfo();
        if ( userInfo != null && user == null && password == null )
        {
            user = userInfo;

            int idx = userInfo.indexOf( ':' );
            if ( idx > 0 )
            {
                user = userInfo.substring( 0, idx );
                password = userInfo.substring( idx + 1 );

                final StringBuilder sb = new StringBuilder();
                idx = this.url.indexOf( "://" );
                sb.append( this.url, 0, idx + 3 );

                idx = this.url.indexOf( "@" );
                if ( idx > 0 )
                {
                    sb.append( this.url.substring( idx + 1 ) );
                }

                this.url = sb.toString();
            }
        }

        host = url.getHost();
        if ( url.getPort() < 0 )
        {
            port = url.getProtocol().equals( "https" ) ? 443 : 80;
        }
        else
        {
            port = url.getPort();
        }
    }

    public int getTimeoutSeconds()
    {
        return timeoutSeconds;
    }

    public void setTimeoutSeconds( final int timeoutSeconds )
    {
        this.timeoutSeconds = timeoutSeconds;
    }

    public int getMaxConnections()
    {
        return maxConnections;
    }

    public void setMaxConnections( int maxConnections )
    {
        this.maxConnections = maxConnections;
    }

    public boolean isIgnoreHostnameVerification()
    {
        return ignoreHostnameVerification;
    }

    public void setIgnoreHostnameVerification( boolean ignoreHostnameVerification )
    {
        this.ignoreHostnameVerification = ignoreHostnameVerification;
    }

    @Override
    public String toString()
    {
        return String.format( "RemoteRepository [%s, %s]", getName(), url );
    }

    public boolean isPassthrough()
    {
        return passthrough;
    }

    public void setPassthrough( final boolean passthrough )
    {
        this.passthrough = passthrough;
    }

    public int getCacheTimeoutSeconds()
    {
        return cacheTimeoutSeconds;
    }

    public void setCacheTimeoutSeconds( final int cacheTimeoutSeconds )
    {
        this.cacheTimeoutSeconds = cacheTimeoutSeconds;
    }

    public int getMetadataTimeoutSeconds()
    {
        return metadataTimeoutSeconds;
    }

    public void setMetadataTimeoutSeconds( int metadataTimeoutSeconds )
    {
        this.metadataTimeoutSeconds = metadataTimeoutSeconds;
    }

    @JsonIgnore
    public String getKeyPassword()
    {
        return keyPassword;
    }

    public void setKeyPassword( final String keyPassword )
    {
        this.keyPassword = keyPassword;
    }

    @JsonIgnore
    public String getKeyCertPem()
    {
        return keyCertificatePem;
    }

    public void setKeyCertPem( final String keyCertificatePem )
    {
        this.keyCertificatePem = keyCertificatePem;
    }

    @JsonIgnore
    public String getServerCertPem()
    {
        return serverCertificatePem;
    }

    public void setServerCertPem( final String serverCertificatePem )
    {
        this.serverCertificatePem = serverCertificatePem;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public void setProxyHost( final String proxyHost )
    {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort( final int proxyPort )
    {
        this.proxyPort = proxyPort;
    }

    public String getProxyUser()
    {
        return proxyUser;
    }

    public void setProxyUser( final String proxyUser )
    {
        this.proxyUser = proxyUser;
    }

    public String getProxyPassword()
    {
        return proxyPassword;
    }

    public void setProxyPassword( final String proxyPassword )
    {
        this.proxyPassword = proxyPassword;
    }

    public int getNfcTimeoutSeconds()
    {
        return nfcTimeoutSeconds;
    }

    public void setNfcTimeoutSeconds( final int nfcTimeoutSeconds )
    {
        this.nfcTimeoutSeconds = nfcTimeoutSeconds;
    }

    public String getServerTrustPolicy()
    {
        return serverTrustPolicy;
    }

    public void setServerTrustPolicy( final String serverTrustPolicy )
    {
        this.serverTrustPolicy = serverTrustPolicy;
    }

    public Integer getPrefetchPriority()
    {
        return prefetchPriority;
    }

    public void setPrefetchPriority( Integer prefetchPriority )
    {
        this.prefetchPriority = prefetchPriority;
    }

    public boolean isPrefetchRescan()
    {
        return prefetchRescan;
    }

    public void setPrefetchRescan( boolean prefetchRescan )
    {
        this.prefetchRescan = prefetchRescan;
    }

    public String getPrefetchListingType()
    {
        return prefetchListingType;
    }

    public void setPrefetchListingType( String prefetchListingType )
    {
        this.prefetchListingType = prefetchListingType;
    }

    @JsonIgnore
    public String getPrefetchRescanTimestamp()
    {
        return prefetchRescanTimestamp;
    }

    public void setPrefetchRescanTimestamp( String prefetchRescanTimestamp )
    {
        this.prefetchRescanTimestamp = prefetchRescanTimestamp;
    }

    @Override
    public RemoteRepository copyOf()
    {
        return copyOf( getPackageType(), getName() );
    }

    @Override
    public RemoteRepository copyOf( String packageType, String name )
    {
        RemoteRepository repo = new RemoteRepository( getPackageType(), name, getUrl() );
        repo.setServerTrustPolicy( getServerTrustPolicy() );
        repo.setKeyPassword( getKeyPassword() );
        repo.setKeyCertPem( getKeyCertPem() );
        repo.setServerCertPem( getServerCertPem() );
        repo.setCacheTimeoutSeconds( getCacheTimeoutSeconds() );
        repo.setMetadataTimeoutSeconds( getMetadataTimeoutSeconds() );
        repo.setNfcTimeoutSeconds( getNfcTimeoutSeconds() );
        repo.setPassthrough( isPassthrough() );
        repo.setProxyHost( getProxyHost() );
        repo.setHost( getHost() );
        repo.setPort( getPort() );
        repo.setProxyPort( getProxyPort() );
        repo.setProxyPassword( getProxyPassword() );
        repo.setProxyUser( getProxyUser() );
        repo.setTimeoutSeconds( getTimeoutSeconds() );
        repo.setMaxConnections( getMaxConnections() );
        repo.setIgnoreHostnameVerification( isIgnoreHostnameVerification() );
        repo.setUser( getUser() );
        repo.setPassword( getPassword() );
        repo.setPrefetchListingType( getPrefetchListingType() );
        repo.setPrefetchPriority( getPrefetchPriority() );
        repo.setPrefetchRescan( isPrefetchRescan() );
        repo.setPrefetchRescanTimestamp( getPrefetchRescanTimestamp() );

        copyRestrictions( repo );
        copyBase( repo );
        return repo;
    }

    @Override
    public void writeExternal( final ObjectOutput out ) throws IOException
    {
        super.writeExternal( out );

        out.writeInt( STORE_VERSION );

        out.writeObject( url );
        out.writeInt( timeoutSeconds );
        out.writeInt( maxConnections );
        out.writeBoolean( ignoreHostnameVerification );
        out.writeInt( nfcTimeoutSeconds );
        out.writeObject( host );
        out.writeInt( port );
        out.writeObject( user );
        out.writeObject( password );
        out.writeBoolean( passthrough );
        out.writeInt( cacheTimeoutSeconds );
        out.writeInt( metadataTimeoutSeconds );
        out.writeObject( keyPassword );
        out.writeObject( keyCertificatePem );
        out.writeObject( serverCertificatePem );
        out.writeObject( proxyHost );
        out.writeInt( proxyPort );
        out.writeObject( proxyUser );
        out.writeObject( proxyPassword );
        out.writeObject( serverTrustPolicy );
        out.writeObject( prefetchPriority );
        out.writeBoolean( prefetchRescan );
        out.writeObject( prefetchListingType );
        out.writeObject( prefetchRescanTimestamp );
    }

    @Override
    public void readExternal( final ObjectInput in ) throws IOException, ClassNotFoundException
    {
        super.readExternal( in );

        int storeVersion = in.readInt();
        if ( storeVersion > STORE_VERSION )
        {
            throw new IOException( "Cannot deserialize. RemoteRepository version in data stream is: " + storeVersion
                                                   + " but this class can only deserialize up to version: "
                                                   + STORE_VERSION );
        }

        this.url = (String) in.readObject();
        this.timeoutSeconds = in.readInt();
        this.maxConnections = in.readInt();
        this.ignoreHostnameVerification = in.readBoolean();
        this.nfcTimeoutSeconds = in.readInt();
        this.host = (String) in.readObject();
        this.port = in.readInt();
        this.user = (String) in.readObject();
        this.password = (String) in.readObject();
        this.passthrough = in.readBoolean();
        this.cacheTimeoutSeconds = in.readInt();
        this.metadataTimeoutSeconds = in.readInt();
        this.keyPassword = (String) in.readObject();
        this.keyCertificatePem = (String) in.readObject();
        this.serverCertificatePem = (String) in.readObject();
        this.proxyHost = (String) in.readObject();
        this.proxyPort = in.readInt();
        this.proxyUser = (String) in.readObject();
        this.proxyPassword = (String) in.readObject();
        this.serverTrustPolicy = (String) in.readObject();
        this.prefetchPriority = (Integer) in.readObject();
        this.prefetchRescan = in.readBoolean();
        this.prefetchListingType = (String) in.readObject();
        this.prefetchRescanTimestamp = (String) in.readObject();
    }

}
