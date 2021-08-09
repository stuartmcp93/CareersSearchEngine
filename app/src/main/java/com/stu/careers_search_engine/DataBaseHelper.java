package com.stu.careers_search_engine;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class creates a SQLite database to store data in the application. It contains methods
 * to add items and query the database.
 *
 * @Author Stuart McPherson
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //The name of the database
    private static final String DB_NAME = "CAREERS_SEARCH_ENGINE_DB";

    //Current version of the database
    private static final int DB_VERSION = 5;

    DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * This method creates a SQLite database to store data locally in the application.
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table to hold data about jobs
        db.execSQL("CREATE TABLE JOBS_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CAREER_AREA TEXT NOT NULL, " +
                "JOB_TITLE TEXT NOT NULL," +
                "MATCHING_TRAIT TEXT NOT NULL, " +
                "DESCRIPTION TEXT NOT NULL," +
                "AVG_SALARY TEXT NOT NULL);");


        //Agreeableness personality trait matching jobs
        insertCareer(db, "Education", "Nursery Practitioner", "A",
                "A nursery practitioner is someone who cares, guides and supports young" +
                        " children during their early years education. Often referred to as early " +
                        "years practitioners or nursery nurses, nursery practitioners act as role models" +
                        " for children throughout the first five years of their lives.", "19,000");

        insertCareer(db, "Education", "Primary Teacher", "A",
                "Primary school teachers are responsible for teaching approved national" +
                        " curriculum subjects to pupils aged 5-11, guiding them through what is" +
                        " arguably the most important stage of their education", "27,066");

        insertCareer(db, "Education", "Secondary School Teacher", "A",
                "As a secondary school teacher, you'll need to: prepare and deliver" +
                        " lessons to classes of different ages and abilities. mark work, give" +
                        " appropriate feedback and maintain records of pupils' progress and development.",
                "29,618");

        insertCareer(db, "Education", "Special Education Teacher", "A",
                "Special educational needs teachers work with children who need additional" +
                        " support. This includes children with physical disabilities, learning, " +
                        "emotional, behavioural or communication difficulties, conditions such as " +
                        "autism, dyspraxia, dyslexia and ADHD, sensory impairments (for " +
                        "example, visual impairment), sensory processing disorder, and mental" +
                        " health issues. Special educational needs (SEN) teachers may also work" +
                        " with gifted children.", "25,314");

        insertCareer(db, "Education", "Teaching Assistant", "A",
                "Teaching or classroom assistants support teachers by helping to supervise" +
                        " activities in the classroom, working with children on an individual," +
                        " small group or whole class basis. They may also manage other learning " +
                        "support staff and report on children's progress.", "13,964");

        insertCareer(db, "Healthcare", "Acupuncturist", "A",
                "As an acupuncturist, you'll take a holistic approach to the maintenance" +
                        " of health and the management of disease. The correct manipulation of qi is" +
                        " used to treat a range of emotional and physical disorders, including" +
                        " those of a musculoskeletal, respiratory, circulatory and gynaecological nature.",
                "22,957");

        insertCareer(db, "Healthcare", "Child Psychologist", "A",
                "Carefully observe children and young people and respond to what they" +
                        " might be communicating through their behaviour and play." +
                        " provide assessment and treatment of children and adolescents as" +
                        " individuals or in a group. supervise trainee child psychotherapists" +
                        " and other therapists.", "35,645");

        insertCareer(db, "Healthcare", "Clinical Psychologist", "A",
                "As a clinical psychologist, your aim is to reduce the distress and " +
                        "improve the psychological well-being of your clients who may have a" +
                        " variety of mental or physical health conditions", "37,570");

        insertCareer(db, "Healthcare", "Dietitian", "A",
                "Dietitians translate the science of nutrition into everyday information" +
                        " about food and advise people on their food and nutrition choices.",
                "26,858");

        insertCareer(db, "Healthcare", "Nurse", "A",
                "Nurses plan and provide medical and nursing care to patients" +
                        " in hospital, at home or in other settings who are suffering from" +
                        " chronic or acute physical or mental ill health. A caring and" +
                        " compassionate nature, and the ability to deal with emotionally" +
                        " charged and pressured situations are important traits of a nurse.",
                "17,596");

        insertCareer(db, "Healthcare", "Massage Therapist", "A",
                "Massage therapists manipulate clients' muscles and soft tissues, " +
                        "treat sports injuries, and give relief to people with health issues.",
                "21,357");

        insertCareer(db, "Healthcare", "Nutritionist", "A",
                "As a nutritionist, you'll generate, assess and deliver scientific," +
                        " evidence-based nutritional advice in a variety of settings to improve" +
                        " health and well-being and to promote a healthy diet and lifestyle.",
                "24,027");

        insertCareer(db, "Healthcare", "Personal Trainer", "A",
                "A personal trainer creates one-on-one fitness programmes for their" +
                        " clients, motivating and guiding them to achieve their goals.",
                "21,739");

        insertCareer(db, "Healthcare", "Pharmacist", "A",
                "Pharmacists are healthcare professionals responsible for supplying" +
                        " medicines in the most economical and effective way possible. It is an" +
                        " applied medical science. Pharmacists constantly monitor the quality, " +
                        "safety and the use of medicines, requiring a high level of involvement" +
                        " and interaction with patients.", "37,419");

        insertCareer(db, "Healthcare", "Psychologist", "A",
                " Psychologists use psychological therapies and procedures to help " +
                        "clients overcome a range of problems including depression, addiction, " +
                        "anxiety, challenging behaviour, neurological disorders, " +
                        "mental ill health and learning disabilities.", "35,645");

        insertCareer(db, "Healthcare", "Occupational Therapist", "A",
                "As an occupational therapist, you'll empower people to carry out" +
                        " everyday tasks or occupations with more confidence and independence " +
                        "in order to improve their health, quality of life and well-being. " +
                        "Tasks often relate to self-care, work or leisure.", "28,602");

        insertCareer(db, "Marketing & Sales", "Fundraiser", "A",
                "Typical responsibilities include: approaching businesses, local " +
                        "authorities, trusts and individuals to secure funding. organising " +
                        "and helping with fundraising activities such as sponsored or promotional" +
                        " events, house-to-house and street collections. recruiting volunteers and" +
                        " coordinating their work.", "22,772");

        insertCareer(db, "Science & Tech", "Veterinarian", "A",
                "Diagnose and treat sick and injured animals. perform operations. carry" +
                        " out blood analyses, X-rays and scans. provide care for animals in " +
                        "veterinary hospitals. carry out regular health checks and give vaccinations.",
                "34,332");

        insertCareer(db, "Science & Tech", "Veterinarian Technician", "A",
                "As an animal technician, you'll need to: ensure a high level of animal " +
                        "welfare and work to improve the quality of life for laboratory animals." +
                        " Clean cages, pens, trays, equipment and fittings, to keep animals clean" +
                        " and comfortable. Monitor the condition of animals and recognise and" +
                        " resolve any behavioural problems.", "29,883");

        //Conscientiousness personality trait matching jobs
        insertCareer(db, "Arts & Humanities", "Writer", "C",
                "Writers produce literary compositions, articles, reports, books and other" +
                        " texts. Excellent writing and communication skills are obviously essential," +
                        " but so is a determined attitude and the ability to take criticism." +
                        " The vast majority of writers are self-employed or freelance.",
                "23,990");

        insertCareer(db, "Business", "Management Consultant", "C",
                "Management consultants help organisations to solve issues, create value," +
                        " maximise growth and improve business performance. They use their business" +
                        " skills to provide objective advice and expertise, and help an organisation" +
                        " to develop any specialist skills that it may be lacking.", "49,241");

        insertCareer(db, "IT", "Technology Consultant", "C",
                "Your role as a technology consultant is to work in partnership with" +
                        " clients, advising them how to use information technology in order to meet " +
                        "their business objectives or overcome problems. You'll work to improve the " +
                        "structure and efficiency of IT systems in various organisations.",
                "44,221");

        insertCareer(db, "IT", "IT Consultant", "C",
                " IT consultants, work with organizations to help them align their " +
                        "technologies with overall goals and better utilize existing technologies" +
                        " and processes. This role requires excellent analytical skills, extensive" +
                        " technical knowledge, and the ability to understand how technology supports" +
                        " business and organization goals. ",
                "44,221");

        insertCareer(db, "Construction", "Surveyor", "C",
                "Surveyors provide professional advice on a range of construction-related" +
                        " matters. They could be ensuring that new-build properties are built to " +
                        "regulations and specifications; advising on maintenance and repair of" +
                        " existing structures or assessing damage for legal and insurance purposes."
                , "31,608");

        insertCareer(db, "Finance", "Accountant", "C",
                "Preparing accounts and tax returns. Monitoring spending and budgets." +
                        " Auditing and analysing financial performance. Financial forecasting " +
                        "and risk analysis. Advising on how to reduce costs and increase profits.",
                "29,621");

        insertCareer(db, "Finance", "Actuary", "C",
                "An actuary evaluates, manages and advises on financial risks. They use" +
                        " their knowledge of business and economics, together with their" +
                        " understanding of probability theory, statistics and investment theory, " +
                        "to provide strategic, commercial and financial advice.", "59,581");

        insertCareer(db, "Finance", "Bookkeeper", "C",
                "The role of Bookkeeper is to record all company spending, including" +
                        " purchases, invoices and sales and provide accurate information on company" +
                        " outgoings for the purposes of filing accounts. A Bookkeeper will be " +
                        "organised, efficient and skilled at keeping track of the various types of" +
                        " spending by the company.", "22,571");

        insertCareer(db, "Finance", "Claims Adjuster", "C",
                "Claims adjusters are responsible for evaluating insurance claims on" +
                        " behalf of insurance companies in order to determine who is liable." +
                        " They investigate the circumstances of insurance claims by consulting" +
                        " with witnesses, reviewing police reports, and compiling evidence " +
                        "from other sources.", "29,401");

        insertCareer(db, "Finance", "Controller", "C",
                "The controller manages accounting records and is responsible for the " +
                        "production of financial reports. The controller oversees all employees" +
                        " involved in the accounting process, including accounts receivable, " +
                        "accounts payable, payroll, inventory and compliance.", "49,056");

        insertCareer(db, "Finance", "Financial Analyst", "C",
                "Financial analysts are responsible for tracking a company's financial" +
                        " performance against a plan, analyzing business performance and market " +
                        "conditions to create forecasts, and helping senior management make " +
                        "tactical and strategic decisions by providing periodic reports.",
                "33,352");

        insertCareer(db, "Finance", "Insurance Underwriter", "C",
                "Insurance underwriters analyse risk in insurance proposals, determine" +
                        " policy terms and calculate premiums on the basis of actuarial, statistical" +
                        " and background information.", "29,446");

        insertCareer(db, "Healthcare", "Anesthesiologist", "C",
                "Anaesthetists are specialist doctors who are responsible for providing" +
                        " anaesthesia to patients for operations and procedures. They are doctors " +
                        "who have chosen after qualifying to undertake postgraduate specialist " +
                        "training of at least seven years in anaesthesia, intensive care medicine" +
                        " and pain management.", "90,599");

        insertCareer(db, "Healthcare", "Physician (General)", "C",
                "General practitioners (GPs) treat all common medical conditions and refer" +
                        " patients to hospitals and other medical services for urgent and specialist" +
                        " treatment. They focus on the health of the whole person combining physical, " +
                        "psychological and social aspects of care.",
                "52,068");

        insertCareer(db, "Healthcare", "Physician (Specialist)", "C",
                "Specialist physicians are experts in preventing, diagnosing, and treating" +
                        " diseases, physiological and psychiatric disorders, and injuries. " +
                        "They examine patients and give advice and treatment towards the management" +
                        " of patient's health.", "65,485");

        insertCareer(db, "Healthcare", "Podiatrist", "C",
                "As a podiatrist, you'll need to: assess, diagnose, treat and evaluate " +
                        "abnormalities and diseases related to the foot and lower limb in people " +
                        "of all ages. Use therapeutic and surgical techniques to treat foot and " +
                        "lower leg issues (e.g. carrying out nail and soft tissue surgery using " +
                        "local anaesthetic)", "28,725");

        insertCareer(db, "Marketing & Sales", "Copywriter", "C",
                "The copywriter is responsible for taking a client's advertising brief" +
                        " and generating original copy ideas that grab the attention of the target" +
                        " audience. This can include creating straplines, slogans, body copy," +
                        "jingles and scripts. Modifying copy until the client is satisfied.",
                "24,747");

        insertCareer(db, "Public Services", "Detective", "C",
                "Detectives are accredited police officers who work as Serious and" +
                        " Complex Crime Investigators or Specialist Investigators. They're " +
                        "responsible for managing a range of investigations including those " +
                        "concerning robbery, drugs, domestic violence, public and child protection," +
                        " company fraud, cybercrime, homicide and counter-terrorism.", "37,251");

        insertCareer(db, "Public Services", "Armed Forces", "C",
                "operating and maintaining warfare systems, equipment and vehicles. " +
                        "taking on specialist duties and skills such as engineering, air traffic " +
                        "control, training and administration. producing briefings, reports and presentations." +
                        " distributing equipment, resources and manpower.", "32,000");

        insertCareer(db, "Public Services", "Private Investigator", "C",
                "Private Investigators (also known as PIs) are freelance detectives, " +
                        "hired by individuals to secretly collect evidence on a particular case. " +
                        "Taking pictures and compiling evidence. Interviewing witnesses and " +
                        "conducting research. Conducting background checks and running credit " +
                        "reports.", "25,325");

        insertCareer(db, "Science & Tech", "Research Technician", "C",
                "Scientific laboratory technicians carry out the work that allows " +
                        "scientists to concentrate on, and perform, the more complex analytical " +
                        "processes in the laboratory. You'll be involved in a variety of " +
                        "laboratory-based investigations within biological, chemical, " +
                        "physical and life science areas.", "21,550");

        insertCareer(db, "Transport & Logistics", "Pilot", "C",
                "As an airline pilot, you'll fly passengers or cargo on long or " +
                        "short-haul flights for leisure, business or commercial purposes. " +
                        "Pilots usually take turns to fly the plane to avoid fatigue, with one " +
                        "operating the controls, while the other speaks to air traffic control and " +
                        "completes the paperwork.", "70,379");

        //Extroversion personality trait matching jobs
        insertCareer(db, "Business", "Human Resources", "E",
                "As an HR officer, your aim is to ensure that the organisation you work" +
                        " for employs the right balance of staff in terms of skills and experience, " +
                        "and that training and development opportunities are available to " +
                        "colleagues to enhance their performance and achieve the company's " +
                        "business aims.", "25,277");

        insertCareer(db, "Business", "Industrial - Organizational Psychologist", "E",
                "Design performance appraisal systems that enable an organisation to " +
                        "measure, manage and reward the performance of its employees. " +
                        "Design, develop and deliver training and development programmes." +
                        " mediate in situations where there is conflict between employees and" +
                        " management or an employment dispute.", "30,834");

        insertCareer(db, "Business", "Operations Manager", "E",
                "At both large and small organizations, operations managers" +
                        " supervise, hire, and train employees, manage quality assurance programs," +
                        " strategize process improvements, and more. Operations managers are " +
                        "ultimately responsible for maintaining and increasing the efficiency " +
                        "of a business, agency, or organization.", "35,411");

        insertCareer(db, "Business", "Public Relations Manager", "E",
                "Planning publicity strategies and campaigns. writing and producing" +
                        " presentations and press releases. dealing with enquiries from the public," +
                        " the press, and related organisations. organising and attending " +
                        "promotional events such as press conferences, open days, exhibitions, " +
                        "tours and visits.", "33,561");

        insertCareer(db, "Business", "Purchasing Manager", "E",
                "Procurement managers act as the primary point of contact between " +
                        "suppliers and a business. They are responsible for the procurement of " +
                        "products and goods for use in the business by identifying potential " +
                        "supplier sources, screening them and negotiating favorable payment terms.",
                "33,675");

        insertCareer(db, "Business", "Retail Manager", "E",
                "As a retail manager, you'll manage the daily operations of a store or " +
                        "department and will have employees reporting to you. You'll report to an " +
                        "area manager. You'll ensure promotions are run accurately and to the " +
                        "company's standards and make sure that staff are all working towards " +
                        "the target for the day.", "24,770");

        insertCareer(db, "Construction", "Construction Manager", "E",
                "As a construction manager, you'll need to: plan and coordinate a project" +
                        " from start to finish, including organising the schedule of work, costings" +
                        " and budget. plan the work and oversee the buying of necessary materials " +
                        "and equipment. Deal with any unexpected problems that may occur during " +
                        "the project.", "45,199");

        insertCareer(db, "Healthcare", "Counselor", "E",
                "Counsellors work in confidential settings with individuals who are " +
                        "experiencing personal difficulties, to help them overcome their problems" +
                        " and to make appropriate changes to their lives. " +
                        "Counsellors listen to, empathise with, encourage and help to " +
                        "empower individuals.", "23,500");

        insertCareer(db, "Healthcare", "Care Assistant", "E",
                "Personal care includes washing, dressing, bathing, toileting and " +
                        "medication monitoring and/or assistance. Social duties include preparing " +
                        "and cooking meals, essential cleaning, shopping, bill payment and " +
                        "fire maintenance.", "17,622");

        insertCareer(db, "Hospitality & Tourism", "Server (Waiter/Waitress)", "E",
                "Alternative titles for this job include Waitress, waiting staff, server. " +
                        "Waiters serve food and drinks to customers in restaurants and cafes, take " +
                        "orders and handle payments.", "17,681");

        insertCareer(db, "Marketing & Sales", "Account Executive", "E",
                "Meeting clients to discuss their advertising needs. Working with account" +
                        " planners to devise a campaign that meets the client's brief and budget. " +
                        "Presenting campaign ideas and costings to clients. Managing the account's " +
                        "budget and invoicing the client.", "23,747");

        insertCareer(db, "Marketing & Sales", "Account Manager", "E",
                "Account management responsibilities include developing strong " +
                        "relationships with customers, connecting with key business executives and " +
                        "stakeholders and preparing sales reports. Account Managers also answer " +
                        "client queries and identify new business opportunities among existing " +
                        "customers.", "27,822");

        insertCareer(db, "Marketing & Sales", "Event Planner", "E",
                "Event planners develop, plan, and execute a variety of both internal and" +
                        " external events. They oversee all aspects of event planning and " +
                        "management, including identifying and working with a variety of external" +
                        " venues/facilities, negotiating contracts with vendors, and maintaining " +
                        "and managing event budgets.", "21,463");

        insertCareer(db, "Marketing & Sales", "Real Estate Agent", "E",
                "Working as an estate agent, you'll usually specialise in either sales or" +
                        " letting of residential or commercial properties, businesses or land on" +
                        " behalf of your clients. You'll value and market properties, with the aim " +
                        "of negotiating the best price for your client.", "21,350");

        insertCareer(db, "Marketing & Sales", "Sales Manager", "E",
                "Leading the team of salespeople is the sales manager, the person that" +
                        " conducts the company's revenue engine. Sales managers hire and motivate " +
                        "high-performing sales teams, and lead them to generate leads, hit or " +
                        "exceed revenue forecasts, and ultimately meet customer needs.", "40,330");

        insertCareer(db, "Marketing & Sales", "Sales Representative", "E",
                "Sales representatives are responsible for selling a company's products by" +
                        " identifying leads, educating prospects on products through calls, " +
                        "trainings, and presentations, and providing existing customers with " +
                        "exceptional support. Most sales reps have a Bachelor's degree in business" +
                        " or a related field.", "20,519");

        insertCareer(db, "Media & Film", "Journalist", "E",
                "Journalists research, write, edit, proofread and file news stories, " +
                        "features and articles. Their pieces are used on television and radio or " +
                        "within magazines, journals and newspapers, in print and online.",
                "25.081");

        insertCareer(db, "Transport & Logistics", "Warehouse Manager", "E",
                "Any company that stores and distributes materials or products relies " +
                        "on a high-functioning warehouse. The warehouse manager supervises all " +
                        "activities of that facility, which often involves overseeing team " +
                        "performance, expediting the receiving and shipping of goods, and " +
                        "ensuring efficient, organized storage.", "26,753");

        //Neuroticism personality trait matching jobs
        insertCareer(db, "Finance", "Stockbroker", "N",
                "Stockbrokers buy and sell stocks, shares and other securities on behalf" +
                        " of private and commercial clients, through a stock exchange or over the " +
                        "counter, in return for a fee or a commission", "39,548");

        insertCareer(db, "Finance", "Asset Manager", "N",
                "Asset managers manage and monitor a company's assets. This could" +
                        " include property, money, stocks, shares and bonds, commodities, equities " +
                        "and other financial products. As an asset manager, you'd aim to maximise" +
                        " your employer's return on investment.", "58,702");

        insertCareer(db, "Healthcare", "Dentist", "N",
                "Educating patients on their oral hygiene and dental care. Examining " +
                        "patient's teeth and identifying or diagnosing any potential dental " +
                        "problems. Performing dental treatments such as fillings and teeth " +
                        "extractions. Referring patients to a dental hygienist or dental therapist.",
                "51,825");

        insertCareer(db, "Healthcare", "Forensic Psychologist", "N",
                "Your work as a forensic psychologist will mainly relate to the assessment" +
                        " and treatment of criminal behaviour. You'll work not only with prisoners" +
                        " and offenders, but with other professionals involved in the judicial " +
                        "and penal systems as well as with victims of crime.", "27,485");

        insertCareer(db, "Healthcare", "Optometrist", "N",
                "As an optometrist your role is to detect defects in vision, signs of " +
                        "injury, ocular diseases (diseases of the eye) or abnormality, and problems" +
                        " with general health. Offer clinical advice regarding eye health and " +
                        "vision correction. prescribe spectacles or contact lenses.", "37,635");

        insertCareer(db, "Healthcare", "Surgeon", "N",
                "As a surgeon, you'll perform operations by cutting open a patient's " +
                        "body to repair, remove or replace the diseased or damaged part. As well as" +
                        " operating, you'll carry out ward rounds and outpatients clinics and will " +
                        "take on a range of administrative duties and teaching responsibilities.",
                "70,018");

        insertCareer(db, "Public Services", "Social Worker", "N",
                "Social workers support individuals and their families through difficult" +
                        " times and ensure that vulnerable people, including children and adults, " +
                        "are safeguarded from harm. Their role is to help improve outcomes in" +
                        " people's lives.", "30,168");

        insertCareer(db, "Hospitality & tourism", "Chef", "N",
                "Oversees a restaurant's kitchen by managing other members of the food " +
                        "preparation team, deciding what dishes to serve and adjusting orders to " +
                        "meet guests' requests. May assist in prep work, such as chopping " +
                        "vegetables, but more often will be involved in cooking specialty dishes.",
                "28,328");

        insertCareer(db, "Hospitality & tourism", "Cook", "N",
                "A Cook, sometimes called a Chef, prepares and serves meals. Their duties" +
                        " include preparing and measuring out ingredients according to recipes, " +
                        "plating meals and maintaining condiments and foodstuff.", "19,487");

        insertCareer(db, "Public Service", "Correctional Officer", "N",
                "Correctional officers oversee inmates to ensure they are safe, secure," +
                        " and supervised. They do so by enforcing outlined rules and regulations." +
                        " This might include inspecting cells, writing incident reports, " +
                        "and restraining inmates. Correctional officers must be alert, decisive, " +
                        "and have good judgment.", "27,095");

        insertCareer(db, "Public Service", "Firefighter", "N",
                "As a firefighter, you'll respond to emergency situations and protect " +
                        "people, the environment and property from all types of accident and " +
                        "emergencies. You'll work closely with the local community to increase " +
                        "their level of fire safety awareness, in order to help prevent fires " +
                        "and accidents happening in the first place.", "29,877");

        insertCareer(db, "Public Service", "Paramedic", "N",
                "Paramedics are emergency response medical professionals, trained in " +
                        "making quick diagnoses and administering immediate medical care to victims " +
                        "of accidents, violence and sudden illnesses, such as cardiac arrests or " +
                        "strokes. Paramedics respond to emergency calls and work under intense" +
                        " pressure.", "25,813");

        insertCareer(db, "Public Service", "Security Officer", "N",
                "A Security Officer, or Security Guard, ensures the safety of the area" +
                        " they are assigned to watch. Their duties include monitoring the premises," +
                        " responding to emergency situations and keeping track of all incidents" +
                        " on-site.", "23,906");

        insertCareer(db, "Public Service", "Civil Servant", "N",
                "The Civil Service delivers public services and supports the government of" +
                        " the day to develop and implement its policies. Civil servants are " +
                        "politically impartial. Together, we ensure the effective running of " +
                        "government and provide the best possible services to the public.",
                "28,180");

        //Openness personality trait matching jobs
        insertCareer(db, "Arts & Humanities", "Artist", "O",
                "Artist provides artistic support for company advertising and/or " +
                        "organization promotional efforts. Develops, sketches, and/or prints " +
                        "creative ideas for a variety of mediums. The Artist works independently " +
                        "within established procedures associated with the specific job function.",
                "24,259");

        insertCareer(db, "Arts & Humanities", "Fashion Designer", "O",
                "create or visualise an idea and produce a design by hand or using " +
                        "computer-aided design (CAD) create moodboards to show to clients. keep up" +
                        " to date with emerging fashion trends as well as general trends relating " +
                        "to fabrics, colours and shapes. plan and develop ranges, " +
                        "often based on a theme.", "26,711");

        insertCareer(db, "Arts & Humanities", "Interior Designer", "O",
                "Interior and spatial designers are involved in the design or renovation" +
                        " of internal spaces, including structural alterations, furnishings, " +
                        "fixtures and fittings, lighting and colour schemes. You may work in a " +
                        "range of commercial, leisure or domestic settings.", "25,659");

        insertCareer(db, "Business", "Executive", "O",
                "Top executives typically do the following: Establish and carry out " +
                        "departmental or organizational goals, policies, and procedures. Direct " +
                        "and oversee an organization's financial and budgetary activities. Manage " +
                        "general activities related to making products and providing services.",
                "23,747");

        insertCareer(db, "Hospitality & Tourism", "Travel Agent", "O",
                "A travel agent's role is to help people plan, choose and arrange their " +
                        "holiday. They will usually work to a budget set out by whoever is " +
                        "planning the holiday. They also offer advice and opinions on where to " +
                        "go and local tourist attractions, events and customs.", "17,770");

        insertCareer(db, "Public Services", "Hairdresser", "O",
                "A Hair Stylist, also known as a Hairdresser, is a professional who " +
                        "colours, cuts, treats and styles hair. Their main duties include " +
                        "consulting with and advising clients, delivering a good-looking, " +
                        "successful end results that the client specified during the " +
                        "consultation process.", "18,078");

        insertCareer(db, "Public Services", "Lawyer", "O",
                "Lawyers advise clients on all aspects of the law and present cases at " +
                        "court proceedings and hearings. Being a lawyer involves advising clients " +
                        "on criminal and civil law and representing them in legal proceedings.",
                "46,408");

        insertCareer(db, "Public Services", "Solicitor", "O",
                "Solicitors take instructions from clients and advise on necessary " +
                        "courses of legal action. Clients can be individuals, groups, public sector" +
                        " organisations or private companies.", "62,000");

        insertCareer(db, "Marketing & Sales", "Art Director", "O",
                "An art director's tasks and duties may include: working from a brief " +
                        "with a copywriter, generating ideas to present to the client. working on " +
                        "designs to produce an effective advertising campaign. commissioning " +
                        "specialists, such as artists and photographers, to work on projects.",
                "36,733");

        insertCareer(db, "Marketing & Sales", "Graphic Designer", "O",
                "Graphic designers/artists design graphics for use in media products " +
                        "such as magazines, labels, advertising and signage. " +
                        "Developing concepts, graphics and layouts for product illustrations, " +
                        "company logos and websites. determining size and arrangement of copy " +
                        "and illustrative material, as well as font style and size.", "23,258");

        insertCareer(db, "Marketing & Sales", "Marketing Manager", "O",
                "A marketing manager builds that awareness by developing and executing" +
                        " on marketing strategies to meet consumer need — and maximize profits." +
                        " Working in industries as varied as advertising, hospitality, healthcare," +
                        " finance, technology, retail, and education, marketing managers are" +
                        " integral to a company's success.", "34,085");


        insertCareer(db, "Media & Film", "Editor", "O",
                "As a film/video editor, you'll manage material such as camera footage," +
                        " dialogue, sound effects, graphics and special effects to produce a final" +
                        " film or video product. This is a key role in the post-production process " +
                        "and your skills can determine the quality and delivery of the finished " +
                        "result.", "24,677");

        insertCareer(db, "Media & Film", "Producer", "O",
                "Producers are at the financial, practical and creative heart of a " +
                        "film or TV drama. They decide on the scale and budget of the film and " +
                        "source financing from investors, studios and distributors. They work with " +
                        "creative ideas from the director and approve production costs.",
                "41,160");

        insertCareer(db, "Transport & Logistics", "Flight Attendant", "O",
                "As a cabin crew member, you'll provide excellent customer service to " +
                        "passengers while ensuring their comfort and safety throughout the flight. " +
                        "You'll help passengers board the plane and give a demonstration of safety " +
                        "procedures and equipment.", "20,532");

        insertCareer(db, "IT", "Web Developer", "O",
                "Your primary task as a web developer will be to create reliable and" +
                        " high-performing applications and services, which can be accessed over " +
                        "the internet.", "25,911");

        insertCareer(db, "IT", "Software Engineer", "O",
                "As a software engineer, you'll work in a constantly evolving environment," +
                        " due to technological advances and the strategic direction of the " +
                        "organisation you work for. You'll create, maintain, audit and improve " +
                        "systems to meet particular needs, often as advised by a systems analyst or" +
                        " architect, testing both hard and software systems to diagnose and " +
                        "resolve system faults.", "37,884");


        //Creating users table
        db.execSQL("CREATE TABLE USER_TABLE( USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT NOT NULL UNIQUE, " +
                "EMAIL_ADDRESS TEXT NOT NULL, " +
                "PASSWORD TEXT NOT NULL);");


        //Insert a user
        insertUser(db, "stuartM", "stuart@gmail.com", "password");


        //Create table to hold user personality test scores
        db.execSQL("CREATE TABLE USER_PERSONALITY_TEST_SCORE(USERNAME TEXT NOT NULL, " +
                "E_SCORE INTEGER, " +
                "A_SCORE INTEGER, " +
                "C_SCORE INTEGER, " +
                "N_SCORE INTEGER, " +
                "O_SCORE INTEGER, " +
                "FOREIGN KEY(USERNAME) REFERENCES USER_TABLE(USERNAME));");

        //Create table to store user favourites list
        db.execSQL("CREATE TABLE USER_FAVOURITES_TABLE(USERNAME TEXT, JOB_ID INTEGER, " +
                "FOREIGN KEY(USERNAME) REFERENCES USER_TABLE(USERNAME)," +
                "FOREIGN KEY(JOB_ID) REFERENCES JOBS_TABLE(_id));");

        //Create table to hold questions for the personality quiz
        db.execSQL("CREATE TABLE PERSONALITY_QUESTIONS(QUESTION_NUM INTEGER PRIMARY KEY AUTOINCREMENT," +
                " QUESTION TEXT NOT NULL," +
                " TRAIT TEXT NOT NULL, " +
                "SCORING INTEGER NOT NULL);");

        //Insert questions for the quiz
        //1
        insertPersonalityQuestion(db, "I am the life of the party.", "E", 1);
        insertPersonalityQuestion(db, "I feel little concern for others.", "A", 0);
        insertPersonalityQuestion(db, "I am always prepared.", "C", 1);
        insertPersonalityQuestion(db, "I get stressed out easily.", "N", 0);
        insertPersonalityQuestion(db, "I have a rich vocabulary.", "O", 1);
        insertPersonalityQuestion(db, "I don't talk a lot.", "E", 0);
        insertPersonalityQuestion(db, "I am interested in people.", "A", 1);
        insertPersonalityQuestion(db, "I leave my belongings around.", "C", 0);
        insertPersonalityQuestion(db, "I am relaxed most of the time.", "N", 1);
        insertPersonalityQuestion(db, "I have difficulty understanding abstract ideas.", "O", 0);
        //10
        insertPersonalityQuestion(db, "I feel comfortable around people.", "E", 1);
        insertPersonalityQuestion(db, "I insult people.", "A", 0);
        insertPersonalityQuestion(db, "I pay attention to details.", "C", 1);
        insertPersonalityQuestion(db, "I worry about things.", "N", 0);
        insertPersonalityQuestion(db, "I have a vivid imagination.", "O", 1);
        insertPersonalityQuestion(db, "I keep in the background.", "E", 0);
        insertPersonalityQuestion(db, "I sympathize with others' feelings.", "A", 1);
        insertPersonalityQuestion(db, "I make a mess of things.", "C", 0);
        insertPersonalityQuestion(db, "I seldom feel blue.", "N", 1);
        insertPersonalityQuestion(db, "I am not interested in abstract ideas.", "O", 0);
        //20
        insertPersonalityQuestion(db, "I start conversations.", "E", 1);
        insertPersonalityQuestion(db, "I am not interested in other people's problems.", "A", 0);
        insertPersonalityQuestion(db, "I get chores done right away.", "C", 1);
        insertPersonalityQuestion(db, "I am easily disturbed.", "N", 0);
        insertPersonalityQuestion(db, "I have excellent ideas.", "O", 1);
        insertPersonalityQuestion(db, "I have little to say.", "E", 0);
        insertPersonalityQuestion(db, "I have a soft heart.", "A", 1);
        insertPersonalityQuestion(db, "I often forget to put things back in their proper place.", "C", 0);
        insertPersonalityQuestion(db, "I get upset easily.", "N", 0);
        insertPersonalityQuestion(db, "I do not have a good imagination.", "O", 0);
        //30
        insertPersonalityQuestion(db, "I talk to a lot of different people at parties.", "E", 1);
        insertPersonalityQuestion(db, "I am not really interested in others", "A", 0);
        insertPersonalityQuestion(db, "I like order.", "C", 1);
        insertPersonalityQuestion(db, "I change my mood a lot.", "N", 0);
        insertPersonalityQuestion(db, "I am quick to understand things", "O", 1);
        insertPersonalityQuestion(db, "I don't like to draw attention to myself", "E", 0);
        insertPersonalityQuestion(db, "I take time out for others.", "A", 1);
        insertPersonalityQuestion(db, "I shirk my duties", "C", 0);
        insertPersonalityQuestion(db, "I have frequent mood swings.", "N", 0);
        insertPersonalityQuestion(db, "I use difficult words.", "O", 1);
       //40
        insertPersonalityQuestion(db, "I don't mind being the center of attention.", "E", 1);
        insertPersonalityQuestion(db, "I feel others' emotions.", "A", 1);
        insertPersonalityQuestion(db, "I follow a schedule.", "C", 1);
        insertPersonalityQuestion(db, "I get irritated easily.", "N", 0);
        insertPersonalityQuestion(db, "I spend time reflecting on things.", "O", 1);
        insertPersonalityQuestion(db, "I am quiet around strangers.", "E", 0);
        insertPersonalityQuestion(db, "I make people feel at ease.", "A", 1);
        insertPersonalityQuestion(db, "I am exacting in my work.", "C", 1);
        insertPersonalityQuestion(db, "I often feel blue.", "N", 0);
        insertPersonalityQuestion(db, "I am full of ideas.", "O", 1);



    }

    /**
     * This method upgrades the database if the old version is less than the new version.
     *
     * @param db         SQLite database
     * @param oldVersion old version of database
     * @param newVersion latest version database
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            //Drop existing tables
            db.execSQL("DROP TABLE IF EXISTS JOBS_TABLE");
            db.execSQL("DROP TABLE IF EXISTS USER_TABLE");
            db.execSQL("DROP TABLE IF EXISTS USER_PERSONALITY_TEST_SCORE");
            db.execSQL("DROP TABLE IF EXISTS USER_FAVOURITES_TABLE");
            db.execSQL("DROP TABLE IF EXISTS PERSONALITY_QUESTIONS");

            //Create updated version of the database
            onCreate(db);
        }

    }

    /**
     * This method inserts a career into the database.
     *
     * @param db            SQLite database
     * @param careerArea    career area
     * @param jobTitle      job title
     * @param matchingTrait matching personality trait
     * @param description   a description of the job
     * @param salary        the average salary (UK)
     */
    private static void insertCareer(SQLiteDatabase db, String careerArea, String jobTitle,
                                     String matchingTrait, String description, String salary) {

        //Create content values object to insert
        ContentValues jobValues = new ContentValues();

        //Add parameter values
        jobValues.put("CAREER_AREA", careerArea);
        jobValues.put("JOB_TITLE", jobTitle);
        jobValues.put("MATCHING_TRAIT", matchingTrait);
        jobValues.put("DESCRIPTION", description);
        jobValues.put("AVG_SALARY", salary);

        //Insert into the database
        db.insert("JOBS_TABLE", null, jobValues);
    }

    /**
     * This method inserts questions for the personality quiz into the database.
     *
     * @param db       SQLite database
     * @param question the question text
     * @param trait    the personality trait being measured
     * @param scoring  scoring value to check if score is added or subtracted from total
     */
    private static void insertPersonalityQuestion(SQLiteDatabase db, String question, String trait, int scoring) {

        //create new content values object
        ContentValues questionValues = new ContentValues();

        //Add parameters to content values
        questionValues.put("QUESTION", question);
        questionValues.put("TRAIT", trait);
        questionValues.put("Scoring", scoring);

        //Insert into the database
        db.insert("PERSONALITY_QUESTIONS", null, questionValues);
    }

    /**
     * This method inserts the users score for the personality quiz into the database.
     *
     * @param db       SQLite database
     * @param username the current user
     * @param eScore   extroversion score
     * @param aScore   agreeableness score
     * @param cScore   conscientiousness score
     * @param nScore   neuroticism score
     * @param oScore   openness score
     */
    public void insertUserScores(SQLiteDatabase db, String username, int eScore,
                                 int aScore, int cScore, int nScore, int oScore) {

        //Delete user score if already exists
        db.delete("USER_PERSONALITY_TEST_SCORE", "USERNAME = ?",
                new String[]{username});

        //Create content values object
        ContentValues scoreValues = new ContentValues();

        //Add parameter values to content values
        scoreValues.put("USERNAME", username);
        scoreValues.put("E_SCORE", eScore);
        scoreValues.put("A_SCORE", aScore);
        scoreValues.put("C_SCORE", cScore);
        scoreValues.put("N_SCORE", nScore);
        scoreValues.put("O_SCORE", oScore);

        //insert into the database
        db.insert("USER_PERSONALITY_TEST_SCORE", null, scoreValues);

    }


    /**
     * This method inserts a new user into the database.
     *
     * @param db       SQLite database
     * @param username the username to add
     * @param emailAdd email address of user
     * @param pWord    password
     */
    public void insertUser(SQLiteDatabase db, String username, String emailAdd,
                           String pWord) {

        //Create content values object
        ContentValues userValues = new ContentValues();

        //Add parameter values
        userValues.put("USERNAME", username);
        userValues.put("EMAIL_ADDRESS", emailAdd);
        userValues.put("PASSWORD", pWord);

        //Insert into the database
        db.insert("USER_TABLE", null, userValues);
    }

    /**
     * This method returns a Question object from the database for use in the personality quiz.
     *
     * @param questionNum the question number
     * @return Question object that matches the question number
     */
    public Question getQuestion(int questionNum) {

        //Create new question object
        Question question = null;

        //Query for the database
        String query = "SELECT * FROM PERSONALITY_QUESTIONS WHERE QUESTION_NUM = ?";

        //Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor to read results of query
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(questionNum)});

        //Retrieve row from cursor and create Question object
        if (cursor.moveToFirst()) {
            question = new Question(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3));
        }

        //close cursor and database
        cursor.close();
        db.close();

        //return question object
        return question;
    }

    /**
     * This method counts all the questions in the database to display total amount of questions
     * and end quiz when questions have finished.
     *
     * @return count of questions in the database
     */
    public int countQuestions() {

        //Set count variable
        int count = 0;

        //SQl query
        String query = "SELECT * FROM PERSONALITY_QUESTIONS";

        //Get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor and run query
        Cursor cursor = db.rawQuery(query, null);

        //Iterate through rows and increment count
        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        //close cursor and databse
        cursor.close();
        db.close();

        //return count
        return count;
    }


    /**
     * This method returns a HashMAp with the current users scores for each personality trait.
     *
     * @param username the current user
     * @return HashMap with the users scores for each of the five personality traits measured.
     */
    public HashMap<String, Integer> getUserPTScores(String username) {

        //Create HashMap to hold result
        HashMap<String, Integer> resultMap = new HashMap<>();

        //SQL query
        String query = "SELECT E_SCORE, A_SCORE, C_SCORE, N_SCORE, O_SCORE " +
                "FROM USER_PERSONALITY_TEST_SCORE WHERE USERNAME = ?;";

        //Get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor and run query
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.moveToFirst()) {
            resultMap.put("eScore", cursor.getInt(0));
            resultMap.put("aScore", cursor.getInt(1));
            resultMap.put("cScore", cursor.getInt(2));
            resultMap.put("nScore", cursor.getInt(3));
            resultMap.put("oScore", cursor.getInt(4));

        }

        //Close cursor and database
        cursor.close();
        db.close();

        //Return the HashMap
        return resultMap;
    }


    /**
     * This method returns a list of Career objects that match the users highest scoring
     * personality trait.
     *
     * @param highestTrait the highest scoring personality trait
     * @return List of Career objects.
     */
    public List<Career> getHighMatchingJobs(String highestTrait) {

        //List to hold Career objects
        List<Career> returnList = new ArrayList<>();

        //SQL query
        String query = "SELECT * FROM JOBS_TABLE WHERE MATCHING_TRAIT LIKE ?;";

        //Get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor and run query
        Cursor cursor = db.rawQuery(query, new String[]{highestTrait});

        if (cursor.moveToFirst()) {
            //Loop through the results
            do {
                //Get values from columns in each row in the cursor
                int id = cursor.getInt(0);
                String careerArea = cursor.getString(1);
                String jobTitle = cursor.getString(2);
                String matchingTrait = cursor.getString(3);
                String description = cursor.getString(4);
                String salary = cursor.getString(5);

                //Create new Career object
                Career career = new Career(id, careerArea, jobTitle, matchingTrait,
                        description, salary);

                //Add new Career object to List
                returnList.add(career);

            } while (cursor.moveToNext());

        }

        //Close cursor and database
        cursor.close();
        db.close();

        //Return List
        return returnList;
    }

    /**
     * This method returns a list of Career objects that match the users lowest scoring
     * personality trait.
     *
     * @param lowestTrait lowest scoring personality trait for the user
     * @return List of Career objects that match the lowest personality trait
     */
    public List<Career> getLowMatchingJobs(String lowestTrait) {

        //List to hold the results
        List<Career> returnList = new ArrayList<>();

        //SQL query
        String query = "SELECT * FROM JOBS_TABLE WHERE MATCHING_TRAIT LIKE ?;";

        //Get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor and run query
        Cursor cursor = db.rawQuery(query, new String[]{lowestTrait});


        if (cursor.moveToFirst()) {
            //Loop through the results
            do {
                //Get values from columns in each row in the cursor
                int id = cursor.getInt(0);
                String careerArea = cursor.getString(1);
                String jobTitle = cursor.getString(2);
                String matchingTrait = cursor.getString(3);
                String description = cursor.getString(4);
                String salary = cursor.getString(5);

                //Create new Career object
                Career career = new Career(id, careerArea, jobTitle, matchingTrait,
                        description, salary);

                //Add career to List
                returnList.add(career);

            } while (cursor.moveToNext());

        }

        //Close cursor and database
        cursor.close();
        db.close();

        //return List
        return returnList;
    }

    /**
     * This method retrieves the current users favourite list of jobs from the database.
     *
     * @param username the current user
     * @return List of Career objects
     */
    public List<Career> getUserFavouriteList(String username) {

        //List to hold results
        List<Career> returnList = new ArrayList<>();

        //SQL query
        String query = "SELECT * FROM JOBS_TABLE WHERE _id IN (" +
                "SELECT JOB_ID FROM USER_FAVOURITES_TABLE WHERE USERNAME LIKE ?);";

        //Get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor and run query
        Cursor cursor = db.rawQuery(query, new String[]{username});


        if (cursor.moveToFirst()) {
            //Loop through the results
            do {
                //Get values from columns
                int id = cursor.getInt(0);
                String careerArea = cursor.getString(1);
                String jobTitle = cursor.getString(2);
                String matchingTrait = cursor.getString(3);
                String description = cursor.getString(4);
                String salary = cursor.getString(5);

                //Create new Career object
                Career career = new Career(id, careerArea, jobTitle, matchingTrait,
                        description, salary);

                //Add Career to list
                returnList.add(career);

            } while (cursor.moveToNext());

        }

        //close cursor and database
        cursor.close();
        db.close();

        //return the List
        return returnList;
    }

    /**
     * A method to check the username and password of a user in the database.
     *
     * @param username the username entered
     * @param password the password enetered
     * @return true if a match is found
     */

    public boolean checkUsernameAndPassword(String username, String password) {
        //Iniate boolean value
        boolean match = false;

        //SQL query
        String query = "SELECT * FROM USER_TABLE WHERE USERNAME = ? AND PASSWORD = ?;";

        //Open database
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        //Set boolean to true if the
        if (cursor.moveToFirst()) {
            match = true;
        }

        //Close cursor and database
        cursor.close();
        db.close();

        //Return boolean
        return match;
    }


    /**
     * This method checks if the username already exists in the database.
     *
     *
     * @param username the username entered
     * @return true if username match found
     */
    public boolean checkUsername(String username) {
        //Initiate boolean variable
        boolean match = false;

        //SQl query
        String query = "SELECT * FROM USER_TABLE WHERE USERNAME = ?;";

        //Open database
        SQLiteDatabase db = this.getReadableDatabase();

        //Open cursor
        Cursor cursor = db.rawQuery(query, new String[]{username});

        //If match found set to true
        if (cursor.moveToFirst()) {
            match = true;
        }

        //close cursor and database
        cursor.close();
        db.close();

        //return boolean value
        return match;
    }
}
