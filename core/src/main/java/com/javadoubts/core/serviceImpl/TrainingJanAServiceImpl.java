package com.javadoubts.core.serviceImpl;

import com.adobe.cq.social.commons.Attachment;
import com.adobe.cq.social.commons.comments.api.CommentCollectionConfiguration;
import com.adobe.cq.social.commons.comments.api.PageInfo;
import com.adobe.cq.social.commons.moderation.api.FlagReason;
import com.adobe.cq.social.forum.client.api.Post;
import com.adobe.cq.social.scf.CollectionPagination;
import com.adobe.cq.social.scf.JsonException;
import com.adobe.cq.social.scf.SocialComponent;
import com.adobe.cq.social.scf.User;
import com.adobe.cq.social.scf.core.CollectionSortedOrder;
import com.adobe.cq.social.scf.core.ResourceID;
import com.javadoubts.core.Config.TrainingConfigService;
import com.javadoubts.core.Config.TrainingJanConfigService;
import com.javadoubts.core.services.TrainingJanAservice;
import com.javadoubts.core.services.TrainingJanService;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component(service = TrainingJanAservice.class)
@Designate(ocd= TrainingJanConfigService.class)
public class TrainingJanAServiceImpl implements TrainingJanAservice {

    private TrainingJanConfigService service;

    @Activate
    protected void activate(TrainingJanConfigService configService){
        service = configService;
    }

    @Override
    public String urlEndpoint() {
        String url = service.getUrlEndpoint();
        File file = new File(
                url);
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        }
        catch (FileNotFoundException e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public String clientid() {
        return service.clientId();
    }
    @Override
    public String clientSecret() {
        return service.clientSecret();
    }

}
